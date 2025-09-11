package org.allaymc.api.network;

import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;

/**
 * PacketReceiver represents an object that can receive packets.
 *
 * @author daoge_cmd
 */
// TODO: remove it
public interface PacketReceiver {
    /**
     * Send a packet.
     *
     * @param packet the chunk packet to send
     */
    void sendPacket(BedrockPacket packet);

    /**
     * Send a packet immediately.
     *
     * @param packet the chunk packet to send
     */
    void sendPacketImmediately(BedrockPacket packet);
}
