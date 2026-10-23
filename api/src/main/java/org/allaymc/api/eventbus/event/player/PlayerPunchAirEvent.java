package org.allaymc.api.eventbus.event.player;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code PlayerPunchAirEvent} represents a player's missed attack input that punches
 * the air. The input processor dispatches this cancellable event before adding the
 * missed-attack sound; cancellation suppresses that sound and does not reverse an
 * earlier arm animation or player input.
 *
 * @author daoge_cmd
 */
@CallerThread(ThreadType.WORLD)
public class PlayerPunchAirEvent extends PlayerEvent implements CancellableEvent {
    /**
     * Creates a {@code PlayerPunchAirEvent} for the supplied player and event data.
     * The constructor retains reference-valued arguments without copying them and
     * does not dispatch or apply the represented action.
     */
    public PlayerPunchAirEvent(EntityPlayer player) {
        super(player);
    }
}
