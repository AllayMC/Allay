/*
 * Copyright (c) 2024, Kaooot
 */
package org.jukeboxmc.extractor

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.netty.bootstrap.Bootstrap
import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.Channel
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioDatagramChannel
import org.cloudburstmc.nbt.NbtMap
import org.cloudburstmc.nbt.NbtType
import org.cloudburstmc.nbt.NbtUtils
import org.cloudburstmc.netty.channel.raknet.RakChannelFactory
import org.cloudburstmc.netty.channel.raknet.config.RakChannelOption
import org.cloudburstmc.protocol.bedrock.BedrockPeer
import org.cloudburstmc.protocol.bedrock.BedrockPong
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec
import org.cloudburstmc.protocol.bedrock.codec.v685.Bedrock_v685
import org.cloudburstmc.protocol.bedrock.data.EncodingSettings
import org.cloudburstmc.protocol.bedrock.netty.initializer.BedrockChannelInitializer
import org.cloudburstmc.protocol.bedrock.util.EncryptionUtils
import org.jukeboxmc.extractor.auth.MsaAuth
import org.jukeboxmc.extractor.config.Config
import org.jukeboxmc.extractor.session.DownstreamPacketHandler
import org.jukeboxmc.extractor.session.ProxiedBedrockClientSession
import org.jukeboxmc.extractor.session.ProxiedBedrockServerSession
import org.jukeboxmc.extractor.session.UpstreamPacketHandler
import org.jukeboxmc.extractor.util.NbtBlockDefinitionRegistry
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.net.InetSocketAddress
import java.security.KeyPair
import java.util.concurrent.atomic.AtomicBoolean

/**
 * @author Kaooot
 * @version 1.0
 */
class DataExtractor {

    private val codec = Bedrock_v685.CODEC
    private val msaAuth = MsaAuth()
    private val running = AtomicBoolean(true)
    private val gson = GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create()
    private val legacyItemIds = HashMap<Int, String>()

    private lateinit var address: InetSocketAddress
    private lateinit var proxyAddress: InetSocketAddress
    private lateinit var blockDefinitionRegistry: NbtBlockDefinitionRegistry
    private lateinit var blockDefinitionHashedRegistry: NbtBlockDefinitionRegistry
    private lateinit var keyPair: KeyPair
    private lateinit var client: Channel

    private var serverSession: ProxiedBedrockServerSession? = null
    private var clientSession: ProxiedBedrockClientSession? = null

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            DataExtractor().boot(args)
        }
    }

    fun boot(args: Array<String>) {
        val file = File("src/main/resources/config.json")

        var config: Config

        if (!file.exists()) {
            file.createNewFile()

            FileOutputStream(file).use {
                config = Config("0.0.0.0", 19122, "127.0.0.1", 19132)

                it.write(this.gson.toJson(config).toByteArray())
            }
        } else {
            FileInputStream(file).reader().use {
                config = this.gson.fromJson(it, Config::class.java)
            }
        }

        this.proxyAddress = InetSocketAddress(config.proxyAddress, config.proxyPort)
        this.address = InetSocketAddress(config.targetAddress, config.targetPort)

        println("Using version ${this.codec.minecraftVersion} (protocol v${this.codec.protocolVersion}, rakNet v${this.codec.raknetProtocolVersion})")

        if (args.isEmpty()) {
            println("Paste the following link (https://microsoft.com/link) in your browser and enter the code: ${this.msaAuth.generateAuthCode()}")

            do {
                Thread.sleep(5000)
            } while (!this.msaAuth.checkIfFinished())

            println("WARNING: DO NOT SHARE THE FOLLOWING INFORMATION WITH OTHERS")
            println("The authentication was successful!")
            println("You may want to specify the following refresh token as program argument so you do not have to generate a new one every time you use this program.")
            println("Your refresh token: " + this.msaAuth.refreshToken())
        } else {
            this.msaAuth.refreshToken(args.first())

            println("Authentication with the specified refresh token was successful")
        }

        val resourceAsStream = this.javaClass.classLoader.getResourceAsStream("block_palette.nbt")

        if (resourceAsStream == null) {
            println("could not find block palette resource")

            return
        }

        resourceAsStream.use {
            NbtUtils.createGZIPReader(it).use { stream ->
                val nbtMap = stream.readTag() as NbtMap
                val blocks = nbtMap.getList("blocks", NbtType.COMPOUND)

                this.blockDefinitionRegistry = NbtBlockDefinitionRegistry(blocks, false)
                this.blockDefinitionHashedRegistry = NbtBlockDefinitionRegistry(blocks, true)
            }
        }

        this.keyPair = EncryptionUtils.createKeyPair()

        val pong = BedrockPong()
            .edition("MCPE")
            .gameType("Survival")
            .version(this.codec.minecraftVersion)
            .protocolVersion(this.codec.protocolVersion)
            .motd("data-extractor")
            .playerCount(0)
            .maximumPlayerCount(20)
            .subMotd("https://github.com/JukeboxMC/JukeboxMC")
            .nintendoLimited(false)
            .ipv4Port(this.proxyAddress.port)
            .ipv6Port(this.proxyAddress.port)

        val server: Channel = ServerBootstrap()
            .group(NioEventLoopGroup())
            .channelFactory(RakChannelFactory.server(NioDatagramChannel::class.java))
            .option(RakChannelOption.RAK_ADVERTISEMENT, pong.toByteBuf())
            .childHandler(object : BedrockChannelInitializer<ProxiedBedrockServerSession>() {
                override fun createSession0(peer: BedrockPeer?, subClientId: Int): ProxiedBedrockServerSession {
                    val session = ProxiedBedrockServerSession(this@DataExtractor, peer, subClientId)

                    synchronized(this@DataExtractor) {
                        session.peer.codecHelper.encodingSettings = EncodingSettings.builder()
                            .maxListSize(Int.MAX_VALUE)
                            .maxByteArraySize(Int.MAX_VALUE)
                            .maxNetworkNBTSize(Int.MAX_VALUE)
                            .maxItemNBTSize(Int.MAX_VALUE)
                            .maxStringLength(Int.MAX_VALUE)
                            .build()
                        this@DataExtractor.serverSession = session
                    }

                    return session
                }

                override fun initSession(session: ProxiedBedrockServerSession?) {
                    session?.codec = this@DataExtractor.codec
                    session?.packetHandler = UpstreamPacketHandler(this@DataExtractor)
                }
            }).bind(this.proxyAddress)
            .awaitUninterruptibly()
            .channel()

        println("The bedrock server has been started on ${this.proxyAddress}")

        this.createClient()

        while (this.running.get()) {
            synchronized(this@DataExtractor) {
                (this@DataExtractor as Object).wait()
            }
        }

        Runtime.getRuntime().addShutdownHook(Thread {
            run {
                this.client.disconnect()

                server.disconnect()
            }
        })
    }

    fun address(): InetSocketAddress = this.address
    fun gson(): Gson = this.gson
    fun keyPair(): KeyPair = this.keyPair
    fun codec(): BedrockCodec = this.codec
    fun blockDefinitionRegistry(): NbtBlockDefinitionRegistry = this.blockDefinitionRegistry
    fun blockDefinitionHashedRegistry(): NbtBlockDefinitionRegistry = this.blockDefinitionHashedRegistry
    fun clientSession(): ProxiedBedrockClientSession = this.clientSession!!
    fun serverSession(): ProxiedBedrockServerSession = this.serverSession!!
    fun msaAuth(): MsaAuth = this.msaAuth
    fun legacyItemIds(): MutableMap<Int, String> = this.legacyItemIds

    private fun createClient() {
        this.client = Bootstrap()
            .group(NioEventLoopGroup())
            .channelFactory(RakChannelFactory.client(NioDatagramChannel::class.java))
            .option(RakChannelOption.RAK_PROTOCOL_VERSION, this.codec.raknetProtocolVersion)
            .handler(object : BedrockChannelInitializer<ProxiedBedrockClientSession>() {
                override fun createSession0(peer: BedrockPeer?, subClientId: Int): ProxiedBedrockClientSession {
                    val session = ProxiedBedrockClientSession(this@DataExtractor, peer, subClientId)

                    synchronized(this@DataExtractor) {
                        this@DataExtractor.clientSession = session
                    }

                    return session
                }

                override fun initSession(session: ProxiedBedrockClientSession?) {
                    session?.codec = this@DataExtractor.codec
                    session?.packetHandler = DownstreamPacketHandler(this@DataExtractor)
                }
            })
            .connect(this.address, InetSocketAddress("0.0.0.0", 0))
            .awaitUninterruptibly()
            .sync()
            .channel()

        println("The client is connected to ${this.address}")
    }
}