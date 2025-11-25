package org.allaymc.api.eventbus.event.server;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.message.MayContainTrKey;
import org.allaymc.api.player.Player;

/**
 * PlayerQuitEvent will be called when a player quit the server.
 * <p>
 * Different from {@link PlayerDisconnectEvent}, this event will only be called when an already logged in player
 * quit the server. If the client disconnects without logging in, this event will not be called.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.ANY)
public class PlayerQuitEvent extends ServerEvent {

    /**
     * The player who has quit the server.
     */
    protected Player player;
    /**
     * The message that will be broadcast.
     */
    @MayContainTrKey
    @Setter
    protected String quitMessage;

    public PlayerQuitEvent(Player player, String quitMessage) {
        this.player = player;
        this.quitMessage = quitMessage;
    }
}
