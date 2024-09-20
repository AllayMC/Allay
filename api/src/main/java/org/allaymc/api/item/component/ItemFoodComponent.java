package org.allaymc.api.item.component;

import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * @author IWareQ
 */
public interface ItemFoodComponent extends ItemComponent {
    void onEaten(EntityPlayer player);

    boolean canBeAlwaysEaten();

    int getFoodPoints();

    float getSaturationPoints();
}
