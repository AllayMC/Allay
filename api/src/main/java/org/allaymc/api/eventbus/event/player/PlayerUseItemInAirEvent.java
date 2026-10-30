package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code PlayerUseItemInAirEvent} holds the duration supplied when a player finishes
 * sustained item use. The transaction processor clears the using flag before
 * dispatch; listeners can cancel the item callback or change {@code usingTime}, but
 * cancellation does not restore the using flag.
 *
 * @author daoge_cmd
 */
@Getter
@Setter
@CallerThread(ThreadType.WORLD)
public class PlayerUseItemInAirEvent extends PlayerEvent implements CancellableEvent {

    /**
     * {@code usingTime} supplies elapsed item-use time in game ticks. The
     * transaction processor passes the listener-selected value to the item callback
     * after approval.
     */
    protected long usingTime;

    /**
     * Creates a {@code PlayerUseItemInAirEvent} for the supplied player and event
     * data. The constructor retains reference-valued arguments without copying them
     * and does not dispatch or apply the represented action.
     */
    public PlayerUseItemInAirEvent(EntityPlayer player, long usingTime) {
        super(player);
        this.usingTime = usingTime;
    }
}
