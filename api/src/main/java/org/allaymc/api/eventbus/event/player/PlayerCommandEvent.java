package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code PlayerCommandEvent} holds command text submitted by a player before
 * registry execution. The command request processor removes the first packet
 * character before dispatch; listeners can replace {@code command} or cancel the
 * event to prevent execution of that request.
 *
 * @author daoge_cmd
 */
@Getter
@Setter
@CallerThread(ThreadType.WORLD)
public class PlayerCommandEvent extends PlayerEvent implements CancellableEvent {
    /**
     * {@code command} holds unprefixed command text. The command request processor
     * reads this value after approval and executes the listener-selected text.
     */
    protected String command;

    /**
     * Creates a {@code PlayerCommandEvent} for the supplied player and event data.
     * The constructor retains reference-valued arguments without copying them and
     * does not dispatch or apply the represented action.
     */
    public PlayerCommandEvent(EntityPlayer player, String command) {
        super(player);
        this.command = command;
    }
}
