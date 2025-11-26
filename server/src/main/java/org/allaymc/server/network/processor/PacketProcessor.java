package org.allaymc.server.network.processor;

import org.allaymc.api.player.Player;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * @author Cool_Loong
 */
public abstract class PacketProcessor<T extends BedrockPacket> {
    public void handleSync(Player player, T packet, long receiveTime) {
        // Do nothing
    }

    public PacketSignal handleAsync(Player player, T packet, long receiveTime) {
        return PacketSignal.UNHANDLED;
    }

    public abstract BedrockPacketType getPacketType();
}