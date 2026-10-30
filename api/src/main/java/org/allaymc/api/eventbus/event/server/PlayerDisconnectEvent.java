package org.allaymc.api.eventbus.event.server;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.player.Player;

/**
 * {@code PlayerDisconnectEvent} reports a player session's disconnection before
 * container and player-manager cleanup. Unlike {@link PlayerQuitEvent}, this
 * notification also covers
 * connections that never completed login or spawning. The disconnect has
 * already happened, so listeners cannot cancel it or assume an entity exists.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.ANY)
public class PlayerDisconnectEvent extends ServerPlayerEvent {
    /**
     * The reason why the player disconnected.
     */
    protected String disconnectReason;

    /**
     * Creates a disconnection notification carrying the session's supplied reason.
     */
    public PlayerDisconnectEvent(Player player, String disconnectReason) {
        super(player);
        this.disconnectReason = disconnectReason;
    }
}
