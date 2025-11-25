package org.allaymc.api.eventbus.event.server;

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
public class PlayerJoinEvent extends ServerEvent implements CancellableEvent {
    @Getter
    protected Player player;

    public PlayerJoinEvent(Player player) {
        this.player = player;
    }
}
