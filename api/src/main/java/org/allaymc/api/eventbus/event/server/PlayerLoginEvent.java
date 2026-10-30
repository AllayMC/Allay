package org.allaymc.api.eventbus.event.server;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.message.MayContainTrKey;
import org.allaymc.api.player.Player;

/**
 * {@code PlayerLoginEvent} represents a request to complete login after the
 * server capacity check.
 * Acceptance precedes the logged-in state, login-success response, and resource
 * pack information. Cancellation disconnects the player with the event's reason;
 * neither acceptance nor this notification means the player has spawned yet.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.NETWORK)
public class PlayerLoginEvent extends ServerPlayerEvent implements CancellableEvent {
    /**
     * {@code disconnectReason} supplies the text shown to the player when this
     * event is cancelled. Listeners may replace it before dispatch returns.
     */
    @MayContainTrKey
    @Setter
    protected String disconnectReason;

    /**
     * Creates a login request with an initial disconnect reason.
     */
    public PlayerLoginEvent(Player player, String disconnectReason) {
        super(player);
        this.disconnectReason = disconnectReason;
    }
}
