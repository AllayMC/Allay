package org.allaymc.api.eventbus.event.server;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.message.MayContainTrKey;
import org.allaymc.api.player.Player;

/**
 * PlayerQuitEvent will be called when a player quit the server.
 * <p>
 * Different from {@link PlayerDisconnectEvent}, this event will only be called when an already spawned player
 * quit the server. If the client disconnects without spawning in the dimension, this event will not be called.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.ANY)
public class PlayerQuitEvent extends ServerPlayerEvent {
    /**
     * The message that will be broadcast.
     */
    @MayContainTrKey
    @Setter
    protected String quitMessage;

    public PlayerQuitEvent(Player player, String quitMessage) {
        super(player);
        this.quitMessage = quitMessage;
    }
}
