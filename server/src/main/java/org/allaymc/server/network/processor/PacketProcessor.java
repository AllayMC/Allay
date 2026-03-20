package org.allaymc.server.network.processor;

import org.allaymc.api.player.Player;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * @author Cool_Loong | daoge_cmd
 */
public abstract class PacketProcessor<T extends BedrockPacket> {

    /**
     * Handle the packet synchronously on the world thread.
     * <p>
     * This method is called when {@link #handleAsync} returns {@link PacketSignal#UNHANDLED},
     * indicating that the packet needs to be processed on the world thread for thread-safety.
     *
     * @param player      the player who sent the packet
     * @param packet      the packet to handle
     * @param receiveTime the tick at which the packet was received. This is the
     *                    entity tick if the player is in a world, otherwise the server tick.
     */
    public void handleSync(Player player, T packet, long receiveTime) {
        // Do nothing
    }

    /**
     * Handle the packet asynchronously on the network thread.
     * <p>
     * If the packet can be fully handled asynchronously, return {@link PacketSignal#HANDLED}.
     * Otherwise, return {@link PacketSignal#UNHANDLED} and the packet will be forwarded
     * to {@link #handleSync} for processing on the world thread.
     *
     * @param player      the player who sent the packet
     * @param packet      the packet to handle
     * @param receiveTime the tick at which the packet was received. This is the
     *                    entity tick if the player is in a world, otherwise the server tick.
     *
     * @return {@link PacketSignal#HANDLED} if fully processed, {@link PacketSignal#UNHANDLED} to defer to sync handling
     */
    public PacketSignal handleAsync(Player player, T packet, long receiveTime) {
        return PacketSignal.UNHANDLED;
    }

    public abstract BedrockPacketType getPacketType();
}