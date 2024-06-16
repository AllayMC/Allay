/*
 * Copyright (c) 2024, Kaooot
 */
package org.jukeboxmc.extractor.session

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import com.nimbusds.jwt.SignedJWT
import org.cloudburstmc.nbt.NbtMap
import org.cloudburstmc.protocol.bedrock.data.TrimMaterial
import org.cloudburstmc.protocol.bedrock.data.TrimPattern
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition
import org.cloudburstmc.protocol.bedrock.data.definitions.SimpleItemDefinition
import org.cloudburstmc.protocol.bedrock.packet.*
import org.cloudburstmc.protocol.bedrock.util.EncryptionUtils
import org.cloudburstmc.protocol.common.PacketSignal
import org.cloudburstmc.protocol.common.SimpleDefinitionRegistry
import org.jukeboxmc.extractor.DataExtractor
import org.jukeboxmc.extractor.util.ExtractionUtil
import org.jukeboxmc.extractor.util.NbtBlockDefinitionRegistry
import org.jukeboxmc.extractor.util.RecipeUtil
import java.io.File
import java.net.URI
import java.util.*

/**
 * Extracting trim data and recipe data required by AllayMC
 *
 * @author Kaooot
 * @version 1.0
 */
class DownstreamPacketHandler(private val dataExtractor: DataExtractor) : BedrockPacketHandler {

    override fun handle(packet: NetworkSettingsPacket): PacketSignal {
        this.dataExtractor.serverSession().sendPacketImmediately(packet)

        Timer().schedule(object : TimerTask() {
            override fun run() {
                dataExtractor.serverSession().setCompression(packet.compressionAlgorithm)
                dataExtractor.clientSession().setCompression(packet.compressionAlgorithm)

                println("The compression was set to ${packet.compressionAlgorithm}")
            }
        }, 60)

        return PacketSignal.HANDLED
    }

    override fun handle(packet: ServerToClientHandshakePacket): PacketSignal {
        val jwt: SignedJWT = SignedJWT.parse(packet.jwt)
        val x5u: URI = jwt.header.x509CertURL
        val salt = Base64.getDecoder().decode(jwt.jwtClaimsSet.getStringClaim("salt"))
        val publicKey = EncryptionUtils.parseKey(x5u.toASCIIString())
        val secretKey = EncryptionUtils.getSecretKey(this.dataExtractor.keyPair().private, publicKey, salt)

        this.dataExtractor.clientSession().enableEncryption(secretKey)
        this.dataExtractor.clientSession().sendPacketImmediately(ClientToServerHandshakePacket())

        return PacketSignal.HANDLED
    }

    override fun handle(packet: DisconnectPacket): PacketSignal {
        println("disconnected: ${packet.reason} (${packet.kickMessage})")

        this.dataExtractor.clientSession().disconnect()

        return PacketSignal.UNHANDLED
    }

    override fun handle(packet: CompressedBiomeDefinitionListPacket): PacketSignal {
        return PacketSignal.UNHANDLED
    }

    override fun handle(packet: AvailableEntityIdentifiersPacket): PacketSignal {
        return PacketSignal.UNHANDLED
    }

    override fun handle(packet: StartGamePacket): PacketSignal {
        val itemDefinitionRegistry = SimpleDefinitionRegistry.builder<ItemDefinition>()
            .addAll(packet.itemDefinitions)
            .add(SimpleItemDefinition("minecraft:empty", 0, false))
            .build()

        this.dataExtractor.clientSession().peer.codecHelper.itemDefinitions = itemDefinitionRegistry
        this.dataExtractor.serverSession().peer.codecHelper.itemDefinitions = itemDefinitionRegistry

        val blockDefinitionRegistry =
            if (packet.isBlockNetworkIdsHashed) this.dataExtractor.blockDefinitionHashedRegistry() else this.dataExtractor.blockDefinitionRegistry()
        blockDefinitionRegistry.add(NbtBlockDefinitionRegistry.NbtBlockDefinition(0, NbtMap.EMPTY))

        this.dataExtractor.clientSession().peer.codecHelper.blockDefinitions = blockDefinitionRegistry
        this.dataExtractor.serverSession().peer.codecHelper.blockDefinitions = blockDefinitionRegistry

        println("blockNetworkIdsHashed: ${packet.isBlockNetworkIdsHashed}")

        val itemIds = HashMap<Int, String>()

        for (itemDefinition in packet.itemDefinitions) {
            itemIds[itemDefinition.runtimeId] = itemDefinition.identifier

            this.dataExtractor.legacyItemIds()[itemDefinition.runtimeId] = itemDefinition.identifier
        }

        val items = ArrayList<JsonObject>()
        val identifiers = ArrayList(itemIds.values)

        identifiers.sort()

        for (identifier in identifiers) {
            val o = JsonObject()
            o.addProperty("name", identifier)
            o.addProperty(
                "id",
                itemIds.entries.find { entry -> entry.value.equals(identifier, true) }?.key
                    ?: throw RuntimeException("Could not find $identifier in itemIds")
            )

            items.add(o)
        }
        return PacketSignal.UNHANDLED
    }

    override fun handle(packet: CreativeContentPacket): PacketSignal {
        val creativeItems = LinkedList<CreativeItem>()

        for (content in packet.contents) {
            var nbtBase64: String? = null
            var blockStateBase64: String? = null

            if (content.tag != null) {
                nbtBase64 = ExtractionUtil.nbtToBase64(content.tag!!)
            }

            if (content.blockDefinition is NbtBlockDefinitionRegistry.NbtBlockDefinition) {
                val nbtTag = (content.blockDefinition as NbtBlockDefinitionRegistry.NbtBlockDefinition).nbtTag

                if (nbtTag != NbtMap.EMPTY) {
                    val sortedTag = TreeMap(nbtTag)
                    val sortedStates = TreeMap(nbtTag.getCompound("states"))
                    val statesBuilder = NbtMap.builder()
                    statesBuilder.putAll(sortedStates)

                    sortedTag["states"] = statesBuilder.build()

                    val tagBuilder = NbtMap.builder()
                    tagBuilder.putAll(sortedTag)

                    blockStateBase64 = ExtractionUtil.nbtToBase64(tagBuilder.build())
                }
            }

            var damage: Int? = content.damage

            if (damage == 0) {
                damage = null
            }

            creativeItems.add(CreativeItem(content.definition.identifier, damage, nbtBase64, blockStateBase64))
        }
        return PacketSignal.UNHANDLED
    }

    override fun handle(packet: CraftingDataPacket): PacketSignal {
        RecipeUtil.writeRecipes(packet, this.dataExtractor)

        println("extracted recipes")

        return PacketSignal.UNHANDLED
    }

    override fun handle(packet: TrimDataPacket): PacketSignal {
        val trimData = TrimData(packet.patterns, packet.materials)
        ExtractionUtil.writeJson(
            this.dataExtractor.gson().toJson(trimData),
            File("Allay-Data/resources/trim_data.json")
        )

        println("extracted trim data")
        return super.handle(packet)
    }

    data class TrimData(val patterns: List<TrimPattern>, val materials: List<TrimMaterial>)

    data class CreativeContents(val items: List<CreativeItem>)

    data class CreativeItem(
        val id: String,
        val damage: Int?,
        @SerializedName("nbt_b64") val nbtBase64: String?,
        @SerializedName("block_state_b64") val blockStateBase64: String?
    )
}