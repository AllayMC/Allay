package org.allaymc.api.eventbus.event.player;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code PlayerStartUseItemInAirEvent} represents a permitted item's proposed
 * transition into sustained use. The transaction processor checks
 * {@code canUseItemInAir} before dispatch; cancellation prevents setting the using
 * flag and does not fall through to the immediate right-click callback for that
 * input.
 *
 * @author daoge_cmd
 */
@CallerThread(ThreadType.WORLD)
public class PlayerStartUseItemInAirEvent extends PlayerEvent implements CancellableEvent {
    /**
     * Creates a {@code PlayerStartUseItemInAirEvent} for the supplied player and
     * event data. The constructor retains reference-valued arguments without copying
     * them and does not dispatch or apply the represented action.
     */
    public PlayerStartUseItemInAirEvent(EntityPlayer player) {
        super(player);
    }
}
