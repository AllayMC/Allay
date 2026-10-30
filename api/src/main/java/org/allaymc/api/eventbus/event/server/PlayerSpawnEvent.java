package org.allaymc.api.eventbus.event.server;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.message.MayContainTrKey;
import org.allaymc.api.player.Player;

/**
 * {@code PlayerSpawnEvent} represents a request to spawn a login session's
 * newly prepared player entity.
 * The entity already has its controller and initial location, allowing listeners
 * to adjust that location before dimension admission. Cancellation disconnects
 * the player before the spawned state and start-game data are sent.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.NETWORK)
public class PlayerSpawnEvent extends ServerPlayerEvent implements CancellableEvent {
    /**
     * {@code disconnectReason} supplies the text shown to the player when this
     * event is cancelled. Listeners may replace it before dispatch returns.
     */
    @MayContainTrKey
    @Setter
    protected String disconnectReason;

    /**
     * Creates a spawn request with an initial disconnect reason.
     */
    public PlayerSpawnEvent(Player player, String disconnectReason) {
        super(player);
        this.disconnectReason = disconnectReason;
    }
}
