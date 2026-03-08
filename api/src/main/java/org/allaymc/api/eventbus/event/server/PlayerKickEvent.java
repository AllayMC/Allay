package org.allaymc.api.eventbus.event.server;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.player.Player;

/**
 * Called when a player is kicked from the server by using command /kick
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class PlayerKickEvent extends ServerPlayerEvent implements CancellableEvent {
    @Setter
    protected String reason;

    public PlayerKickEvent(Player player, String reason) {
        super(player);
        this.reason = reason;
    }
}
