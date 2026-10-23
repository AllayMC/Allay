package org.allaymc.api.eventbus.event.server;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.player.Player;

/**
 * {@code PlayerKickEvent} represents a kick-command request to disconnect a
 * selected player.
 * Listeners may change the reason before the command formats its disconnect
 * message. Cancellation stops the command's remaining target processing, while
 * players already processed by that command remain disconnected.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class PlayerKickEvent extends ServerPlayerEvent implements CancellableEvent {
    /**
     * {@code reason} supplies the text appended to the standard kick message.
     * Blank text selects the standard message alone; the command requires a
     * non-null value.
     */
    @Setter
    protected String reason;

    /**
     * Creates a command kick request with the supplied initial reason.
     */
    public PlayerKickEvent(Player player, String reason) {
        super(player);
        this.reason = reason;
    }
}
