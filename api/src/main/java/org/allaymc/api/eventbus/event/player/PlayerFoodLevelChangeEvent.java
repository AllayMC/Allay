package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * @author daoge_cmd | Dhaiven
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class PlayerFoodLevelChangeEvent extends PlayerEvent implements CancellableEvent {
    protected int oldFoodLevel;
    @Setter
    protected int newFoodLevel;

    public PlayerFoodLevelChangeEvent(EntityPlayer player, int oldFoodLevel, int newFoodLevel) {
        super(player);
        this.oldFoodLevel = oldFoodLevel;
        this.newFoodLevel = newFoodLevel;
    }
}
