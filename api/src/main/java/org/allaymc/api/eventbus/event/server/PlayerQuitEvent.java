package org.allaymc.api.eventbus.event.server;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.message.MayContainTrKey;
import org.allaymc.api.player.Player;

/**
 * {@code PlayerQuitEvent} reports cleanup of a disconnected player that reached
 * the spawned state.
 * Unlike {@link PlayerDisconnectEvent}, this event excludes earlier login
 * failures. Listeners may replace or suppress the quit announcement before
 * dimension removal and player-data saving, but cannot cancel the disconnect.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.ANY)
public class PlayerQuitEvent extends ServerPlayerEvent {
    /**
     * {@code quitMessage} supplies the translatable announcement receiving the
     * player's original name as an argument. {@code null} or blank text suppresses
     * the quit broadcast.
     */
    @MayContainTrKey
    @Setter
    protected String quitMessage;

    /**
     * Creates a quit notification with the initial broadcast message.
     */
    public PlayerQuitEvent(Player player, String quitMessage) {
        super(player);
        this.quitMessage = quitMessage;
    }
}
