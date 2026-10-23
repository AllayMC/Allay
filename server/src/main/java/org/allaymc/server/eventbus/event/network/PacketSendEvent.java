package org.allaymc.server.eventbus.event.network;

import org.allaymc.api.player.Player;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;

/**
 * {@code PacketSendEvent} exposes an outgoing packet before transport
 * submission. Cancellation suppresses the send. Negotiated sends also reject
 * {@code null}, unregistered, and serverbound replacements; bootstrap sends
 * throw {@code IllegalStateException} for {@code null} replacements or values
 * other than {@code PlayStatusPacket}. Both paths recheck connection state after
 * listener dispatch. Event acceptance does not establish transport delivery.
 *
 * @author daoge_cmd
 */
public class PacketSendEvent extends PacketEvent {
    /**
     * Creates an outgoing-packet event retaining the supplied player and packet
     * references. The constructor does not send or validate {@code packet}; the
     * bootstrap or negotiated dispatcher validates the final listener-selected
     * value after the event is called.
     */
    public PacketSendEvent(Player player, BedrockPacket packet) {
        super(player, packet);
    }
}
