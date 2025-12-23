package org.allaymc.api.eventbus.event.server;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.player.Player;

/**
 * PlayerDisconnectEvent will be called when the player disconnects.
 * <p>
 * Different from {@link PlayerQuitEvent}, this event will always
 * be called when the player disconnects, even if the player is not logged in.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.ANY)
public class PlayerDisconnectEvent extends ServerPlayerEvent {
    /**
     * The player associated with this event.
     */
    protected Player player;

    /**
     * The reason why the player disconnected.
     */
    protected String disconnectReason;

    public PlayerDisconnectEvent(Player player, String disconnectReason) {
        super(player);
        this.disconnectReason = disconnectReason;
    }
}
