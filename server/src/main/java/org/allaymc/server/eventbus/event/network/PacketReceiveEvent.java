package org.allaymc.server.eventbus.event.network;

import org.allaymc.api.player.Player;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;

/**
 * {@code PacketReceiveEvent} exposes an incoming packet before the connection
 * selects its processor. Cancellation or a {@code null} replacement consumes
 * the packet without processor dispatch. The dispatcher looks up an accepted
 * replacement by its packet type using the currently applicable bootstrap or
 * negotiated processor holder.
 *
 * @author daoge_cmd
 */
public class PacketReceiveEvent extends PacketEvent {
    /**
     * Creates an incoming-packet event retaining the supplied player and packet
     * references. The constructor does not dispatch the event or validate its
     * arguments; the receiving dispatcher handles cancellation and any replacement
     * afterward.
     */
    public PacketReceiveEvent(Player player, BedrockPacket packet) {
        super(player, packet);
    }
}
