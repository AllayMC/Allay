package org.allaymc.server.network.processor;

import org.allaymc.server.entity.impl.EntityPlayerImpl;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * @author Cool_Loong
 */
public abstract class PacketProcessor<T extends BedrockPacket> {
    public void handleSync(EntityPlayerImpl player, T packet, long receiveTime) {
        // Do nothing
    }

    public PacketSignal handleAsync(EntityPlayerImpl player, T packet, long receiveTime) {
        return PacketSignal.UNHANDLED;
    }

    public abstract BedrockPacketType getPacketType();
}