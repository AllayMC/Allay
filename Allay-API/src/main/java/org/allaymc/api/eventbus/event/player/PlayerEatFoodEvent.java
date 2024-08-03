package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;

/**
 * Allay Project 2024/8/3
 *
 * @author daoge_cmd
 */
@Getter
public class PlayerEatFoodEvent extends PlayerEvent implements CancellableEvent {
    protected ItemStack food;

    public PlayerEatFoodEvent(EntityPlayer player, ItemStack food) {
        super(player);
        this.food = food;
    }
}
