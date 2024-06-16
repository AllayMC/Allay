/*
 * Copyright (c) 2024, Kaooot
 */
package org.jukeboxmc.extractor.session

import org.cloudburstmc.protocol.bedrock.data.EncodingSettings
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketHandler
import org.cloudburstmc.protocol.bedrock.packet.LoginPacket
import org.cloudburstmc.protocol.bedrock.packet.PlayStatusPacket
import org.cloudburstmc.protocol.bedrock.packet.PlayStatusPacket.Status
import org.cloudburstmc.protocol.bedrock.packet.RequestNetworkSettingsPacket
import org.cloudburstmc.protocol.common.PacketSignal
import org.jukeboxmc.extractor.DataExtractor
import org.jukeboxmc.extractor.auth.AuthExtraData
import org.jukeboxmc.extractor.util.ForgeryUtil
import java.security.interfaces.ECPublicKey

/**
 * @author Kaooot
 * @version 1.0
 */
class UpstreamPacketHandler(private val dataExtractor: DataExtractor) : BedrockPacketHandler {

    private var lastLogin: Long = 0L

    override fun handle(packet: RequestNetworkSettingsPacket): PacketSignal {
        val protocolVersion = packet.protocolVersion

        if (protocolVersion != this.dataExtractor.codec().protocolVersion) {
            val playStatusPacket = PlayStatusPacket()

            if (protocolVersion > this.dataExtractor.codec().protocolVersion) {
                playStatusPacket.status = Status.LOGIN_FAILED_SERVER_OLD
            } else {
                playStatusPacket.status = Status.LOGIN_FAILED_CLIENT_OLD
            }

            this.dataExtractor.serverSession().sendPacketImmediately(playStatusPacket)

            return PacketSignal.HANDLED
        }

        val encodingSettings = EncodingSettings.builder()
            .maxListSize(Int.MAX_VALUE)
            .maxByteArraySize(Int.MAX_VALUE)
            .maxNetworkNBTSize(Int.MAX_VALUE)
            .maxItemNBTSize(Int.MAX_VALUE)
            .maxStringLength(Int.MAX_VALUE)
            .build()
        this.dataExtractor.clientSession().peer.codecHelper.encodingSettings = encodingSettings
        this.dataExtractor.serverSession().peer.codecHelper.encodingSettings = encodingSettings

        println("set encoding settings")

        return PacketSignal.UNHANDLED
    }

    override fun handle(packet: LoginPacket): PacketSignal {
        if (System.currentTimeMillis() - this.lastLogin < 5000L) {
            return PacketSignal.UNHANDLED
        }

        val keyPair = this.dataExtractor.keyPair()
        val loginChain = this.dataExtractor.msaAuth()
            .generateLoginChain((keyPair.public as ECPublicKey), this.dataExtractor.codec().minecraftVersion)
        val authExtraData = AuthExtraData.fromChain(loginChain)

        println("Logging in as ${authExtraData.displayName}")

        packet.chain.clear()
        packet.chain.addAll(ForgeryUtil.forgeChain(keyPair, loginChain))
        packet.extra = ForgeryUtil.forgeSkin(
            keyPair,
            packet.extra,
            "${this.dataExtractor.address().address.hostAddress}:${this.dataExtractor.address().port}",
            authExtraData.displayName
        )

        this.dataExtractor.clientSession().sendPacket(packet)

        this.lastLogin = System.currentTimeMillis()

        return PacketSignal.HANDLED
    }
}