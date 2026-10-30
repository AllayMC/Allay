package org.allaymc.server.network.processor;

import org.allaymc.api.player.Player;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.PacketSignal;

/**
 * {@code PacketProcessor} defines asynchronous admission and synchronous handling
 * for one Bedrock packet type. A protocol session invokes the asynchronous hook first and
 * forwards an unhandled packet to the synchronous hook. The world queue
 * supplies synchronous execution when the player has a world; before that, the
 * session invokes the synchronous hook directly on the receiving thread.
 * Default implementations defer and then do nothing, allowing subclasses to
 * implement only the phase they need.
 *
 * @author Cool_Loong | daoge_cmd
 */
public abstract class PacketProcessor<T extends BedrockPacket> {

    /**
     * Handles work deferred by the asynchronous hook; the default
     * implementation has no effect. Sessions normally call this through the
     * player's world queue, but players without a world use the receiving
     * thread directly.
     *
     * @param receiveTime entity tick when associated with a world, otherwise
     * the server receive tick
     */
    public void handleSync(Player player, T packet, long receiveTime) {
    }

    /**
     * Decides whether further synchronous handling is required; the default
     * always returns {@code UNHANDLED}. Sessions invoke this hook on their receive
     * path, so implementations that consume a packet should return {@code HANDLED}
     * instead of scheduling world work.
     *
     * @param receiveTime entity tick when associated with a world, otherwise
     * the server receive tick
     */
    public PacketSignal handleAsync(Player player, T packet, long receiveTime) {
        return PacketSignal.UNHANDLED;
    }

    /**
     * Returns the packet type that this processor accepts from its holder.
     */
    public abstract BedrockPacketType getPacketType();
}
