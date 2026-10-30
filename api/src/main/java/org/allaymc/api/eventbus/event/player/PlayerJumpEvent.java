package org.allaymc.api.eventbus.event.player;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * {@code PlayerJumpEvent} reports that a player submitted the start-jumping input
 * action. The input processor dispatches this notification before applying
 * jump-related exhaustion; the event is not cancellable and does not expose a
 * replacement motion or a requested jump height.
 *
 * @author daoge_cmd
 */
@CallerThread(ThreadType.WORLD)
public class PlayerJumpEvent extends PlayerEvent {
    /**
     * Creates a {@code PlayerJumpEvent} for the supplied player and event data. The
     * constructor retains reference-valued arguments without copying them and does
     * not dispatch or apply the represented action.
     */
    public PlayerJumpEvent(EntityPlayer player) {
        super(player);
    }
}
