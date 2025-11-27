package org.allaymc.api.eventbus.event.server;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.player.Player;

/**
 * Called when a player joins the server.
 *
 * @author daoge_cmd
 */
@CallerThread(ThreadType.NETWORK)
@AllArgsConstructor
public class PlayerJoinEvent extends ServerEvent implements CancellableEvent {
    /**
     * The player associated with this event.
     */
    @Getter
    protected Player player;
}
