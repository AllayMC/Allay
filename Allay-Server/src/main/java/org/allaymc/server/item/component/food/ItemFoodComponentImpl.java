package org.allaymc.server.item.component.food;

import lombok.Getter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.component.event.ItemTryUseEvent;
import org.allaymc.api.item.component.event.ItemUsedEvent;
import org.allaymc.api.item.component.food.ItemFoodComponent;
import org.allaymc.api.item.type.ItemTypes;

/**
 * Allay Project 07/07/2024
 *
 * @author IWareQ
 */
@Getter
public class ItemFoodComponentImpl implements ItemFoodComponent {

    private static final int DEFAULT_EATING_TIME = 32; // GameTick

    private final int foodPoints;
    private final float saturationPoints;
    private final int eatingTime;

    public ItemFoodComponentImpl(int foodPoints, float saturationPoints) {
        this(foodPoints, saturationPoints, DEFAULT_EATING_TIME);
    }

    public ItemFoodComponentImpl(int foodPoints, float saturationPoints, int eatingTime) {
        this.foodPoints = foodPoints;
        this.saturationPoints = saturationPoints;
        this.eatingTime = eatingTime;
    }

    @Override
    public void onEaten(EntityPlayer player) {
        player.saturate(this.foodPoints, this.saturationPoints);
    }

    @EventHandler
    private void onTryUseItem(ItemTryUseEvent event) {
        event.setCanBeUsed(event.getPlayer().canEat());
    }

    @EventHandler
    private void onItemUsed(ItemUsedEvent event) {
        if (event.getUsedTime() < eatingTime) {
            event.setCanBeUsed(false);
            return;
        }

        var player = event.getPlayer();
        onEaten(player);
        var itemInHand = player.getItemInHand();
        if (itemInHand.getCount() == 1) player.setItemInHand(ItemTypes.AIR_TYPE.createItemStack());
        else itemInHand.setCount(itemInHand.getCount() - 1);
    }
}
