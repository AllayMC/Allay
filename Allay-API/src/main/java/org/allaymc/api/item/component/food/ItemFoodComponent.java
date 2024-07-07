package org.allaymc.api.item.component.food;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.component.ItemComponent;

/**
 * Allay Project 06/07/2024
 *
 * @author IWareQ
 */
public interface ItemFoodComponent extends ItemComponent {
    void onEaten(EntityPlayer player);

    int getFoodPoints();

    float getSaturationPoints();
}
