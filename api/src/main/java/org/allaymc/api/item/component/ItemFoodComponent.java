package org.allaymc.api.item.component;

import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * Represents a food item component.
 *
 * @author IWareQ
 */
public interface ItemFoodComponent extends ItemComponent {
    /**
     * Called when the food is eaten.
     *
     * @param player The player who eats the food.
     */
    void onEaten(EntityPlayer player);

    /**
     * Check if the food can be always eaten.
     * <p>
     * Some food can be always eaten, such as golden apples.
     *
     * @return {@code true} if the food can be always eaten, {@code false} otherwise.
     */
    boolean canBeAlwaysEaten();

    /**
     * Get the food points.
     *
     * @return The food points.
     */
    int getFoodPoints();

    /**
     * Get the saturation points.
     *
     * @return The saturation points.
     */
    float getSaturationPoints();
}
