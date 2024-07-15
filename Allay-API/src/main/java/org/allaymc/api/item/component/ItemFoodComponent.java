package org.allaymc.api.item.component;

import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * Allay Project 06/07/2024
 *
 * @author IWareQ
 */
public interface ItemFoodComponent extends ItemComponent {
    void onEaten(EntityPlayer player);

    boolean canBeAlwaysEaten();

    int getFoodPoints();

    float getSaturationPoints();
}
