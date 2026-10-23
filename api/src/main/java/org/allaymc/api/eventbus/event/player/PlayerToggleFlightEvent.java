package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * {@code PlayerToggleFlightEvent} represents a notification carrying a player
 * entity's flight state. {@code value} records the state supplied by its producer;
 * this event is not cancellable, and the current server implementation has no
 * dispatch site for this event type.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class PlayerToggleFlightEvent extends PlayerEvent {
    /**
     * State reported by this transition.
     */
    protected boolean value;

    /**
     * Creates a {@code PlayerToggleFlightEvent} for the supplied player and event
     * data. The constructor retains reference-valued arguments without copying them
     * and does not dispatch or apply the represented action.
     */
    public PlayerToggleFlightEvent(EntityPlayer player, boolean value) {
        super(player);
        this.value = value;
    }
}
