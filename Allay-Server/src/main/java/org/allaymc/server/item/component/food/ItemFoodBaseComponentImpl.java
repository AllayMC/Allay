package org.allaymc.server.item.component.food;

import lombok.Getter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.food.ItemFoodComponent;
import org.allaymc.api.item.init.ItemStackInitInfo;
import org.allaymc.server.item.component.common.ItemBaseComponentImpl;

/**
 * Allay Project 06/07/2024
 *
 * @author IWareQ
 */
@Getter
public class ItemFoodBaseComponentImpl<T extends ItemStack> extends ItemBaseComponentImpl<T> implements ItemFoodComponent {
    private final int foodPoints;
    private final float saturationPoints;

    public ItemFoodBaseComponentImpl(ItemStackInitInfo<T> initInfo, int foodPoints, float saturationPoints) {
        super(initInfo);
        this.foodPoints = foodPoints;
        this.saturationPoints = saturationPoints;
    }

    @Override
    public void onEaten(EntityPlayer player) {
        player.saturate(this.foodPoints, this.saturationPoints);
    }
}
