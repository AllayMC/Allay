/*
 * Copyright (c) 2024, Kaooot
 */
package org.jukeboxmc.extractor.session

import io.netty.util.ReferenceCountUtil
import org.cloudburstmc.protocol.bedrock.BedrockClientSession
import org.cloudburstmc.protocol.bedrock.BedrockPeer
import org.cloudburstmc.protocol.bedrock.netty.BedrockPacketWrapper
import org.cloudburstmc.protocol.common.PacketSignal
import org.jukeboxmc.extractor.DataExtractor

/**
 * @author Kaooot
 * @version 1.0
 */
class ProxiedBedrockClientSession(private val dataExtractor: DataExtractor, peer: BedrockPeer?, subClientId: Int) :
    BedrockClientSession(peer, subClientId) {

    override fun onPacket(wrapper: BedrockPacketWrapper?) {
        val packet = wrapper?.packet

        if (this.packetHandler == null) {
            println("Received packet without a packet handler for ${this.socketAddress}:${this.subClientId}: " + packet)
        } else if (this.packetHandler.handlePacket(packet).equals(PacketSignal.UNHANDLED)) {
            this.dataExtractor.serverSession().sendPacket(ReferenceCountUtil.retain(packet)!!)
        }
    }
}