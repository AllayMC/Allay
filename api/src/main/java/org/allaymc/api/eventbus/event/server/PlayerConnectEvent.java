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
 * Called when a player connects to the server.
 * <p>
 * This event is called before the player is logged in.
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
@Getter
@CallerThread(ThreadType.NETWORK)
public class PlayerConnectEvent extends ServerEvent implements CancellableEvent {
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
}
