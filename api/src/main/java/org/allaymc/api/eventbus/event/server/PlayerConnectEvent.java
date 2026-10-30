package org.allaymc.api.eventbus.event.server;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.message.MayContainTrKey;
import org.allaymc.api.player.Player;

/**
 * {@code PlayerConnectEvent} represents a newly created player connection
 * awaiting acceptance before login.
 * The network interface emits this event after its IP ban check and player
 * construction. Cancellation disconnects the session with the event's reason
 * before the normal login timeout is scheduled.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.NETWORK)
public class PlayerConnectEvent extends ServerPlayerEvent implements CancellableEvent {
    /**
     * {@code disconnectReason} supplies the reason sent directly to the network
     * session when this event is cancelled. The connection acceptance path does not
     * translate this value itself.
     */
    @MayContainTrKey
    @Setter
    protected String disconnectReason;

    /**
     * Creates a connection request with an initial rejection reason.
     */
    public PlayerConnectEvent(Player player, String disconnectReason) {
        super(player);
        this.disconnectReason = disconnectReason;
    }
}
