package org.allaymc.server.eventbus.event.network;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.player.Player;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;

/**
 * {@code PacketEvent} carries a player and a replaceable packet through
 * cancellable network hooks. Listeners use {@code setPacket} to replace the
 * reference considered by the dispatcher. The generated constructor and setter
 * retain references without validation; the receive, negotiated-send, and
 * bootstrap-send paths define their own rejection rules.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class PacketEvent extends NetworkEvent implements CancellableEvent {
    /**
     * {@code player} identifies the connection supplied by the dispatcher. The
     * generated getter returns the same player reference for event listeners.
     */
    protected Player player;
    /**
     * {@code packet} holds the current packet reference. Listeners may replace
     * it through {@code setPacket}, and the dispatcher reads its final value
     * after listeners return.
     */
    @Setter
    protected BedrockPacket packet;
}
