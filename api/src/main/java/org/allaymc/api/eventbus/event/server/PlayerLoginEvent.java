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
 * Called when a player logged in the server successfully.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.NETWORK)
public class PlayerLoginEvent extends ServerPlayerEvent implements CancellableEvent {
    /**
     * The player associated with this event.
     */
    protected Player player;

    /**
     * The reason that will be shown to the player if the event is cancelled.
     */
    @MayContainTrKey
    @Setter
    protected String disconnectReason;

    public PlayerLoginEvent(Player player, String disconnectReason) {
        super(player);
        this.disconnectReason = disconnectReason;
    }
}
