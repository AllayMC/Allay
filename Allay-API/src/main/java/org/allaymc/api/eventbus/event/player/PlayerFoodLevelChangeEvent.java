package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import lombok.Setter;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Allay Project 2024/8/3
 *
 * @author daoge_cmd | Dhaiven
 */
@Getter
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
