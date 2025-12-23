package org.allaymc.api.eventbus.event.server;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.message.MayContainTrKey;
import org.allaymc.api.player.Player;

/**
 * Called when a player is about to be spawned in the dimension during the login sequence.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.NETWORK)
public class PlayerSpawnEvent extends ServerPlayerEvent implements CancellableEvent {
    /**
     * The reason that will be shown to the player if the event is cancelled.
     */
    @MayContainTrKey
    @Setter
    protected String disconnectReason;

    public PlayerSpawnEvent(Player player, String disconnectReason) {
        super(player);
        this.disconnectReason = disconnectReason;
    }
}
