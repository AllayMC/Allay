package org.allaymc.api.item.component;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.jetbrains.annotations.ApiStatus;

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
    @ApiStatus.OverrideOnly
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
     * Gets the time it takes to eat the food (unit: game tick).
     *
     * @return The eating time in ticks (unit: game tick).
     */
    int getEatingTime();

    /**
     * Checks if the food is a drink.
     * <p>
     * Some food items are drinks, such as milk buckets and potions.
     *
     * @return {@code true} if the food is a drink, {@code false} otherwise.
     */
    boolean isDrink();

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
