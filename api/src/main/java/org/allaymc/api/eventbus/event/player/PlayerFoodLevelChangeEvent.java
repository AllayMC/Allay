package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code PlayerFoodLevelChangeEvent} holds the old and proposed food levels for a
 * player entity. The player component clamps its initial proposal before dispatch,
 * then reads the listener-selected {@code newFoodLevel} without another clamp;
 * cancellation preserves the previously stored food level.
 *
 * @author daoge_cmd | Dhaiven
 */
@Getter
@CallerThread(ThreadType.WORLD)
@CallerThread(ThreadType.DIMENSION)
public class PlayerFoodLevelChangeEvent extends PlayerEvent implements CancellableEvent {
    /**
     * Food level before the update.
     */
    protected int oldFoodLevel;
    /**
     * {@code newFoodLevel} supplies the accepted food level. The player component
     * reads this value after listeners run without repeating its initial clamp.
     */
    @Setter
    protected int newFoodLevel;

    /**
     * Creates a {@code PlayerFoodLevelChangeEvent} for the supplied player and event
     * data. The constructor retains reference-valued arguments without copying them
     * and does not dispatch or apply the represented action.
     */
    public PlayerFoodLevelChangeEvent(EntityPlayer player, int oldFoodLevel, int newFoodLevel) {
        super(player);
        this.oldFoodLevel = oldFoodLevel;
        this.newFoodLevel = newFoodLevel;
    }
}
