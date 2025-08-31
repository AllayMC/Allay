package org.allaymc.api.item.component;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents a edible item component.
 *
 * @author IWareQ
 */
public interface ItemEdibleComponent extends ItemComponent {
    /**
     * Called when the item is eaten.
     *
     * @param player The player who eats the item
     */
    @ApiStatus.OverrideOnly
    void onEaten(EntityPlayer player);

    /**
     * Check if the item can be always eaten.
     * <p>
     * Some item can be always eaten, such as golden apples.
     *
     * @return {@code true} if the item can be always eaten, {@code false} otherwise.
     */
    boolean canBeAlwaysEaten();

    /**
     * Gets the time it takes to eat the item (unit: game tick).
     *
     * @return The eating time in ticks (unit: game tick)
     */
    int getEatingTime();

    /**
     * Checks if the item is a drink.
     * <p>
     * Some item items are drinks, such as milk buckets and potions.
     *
     * @return {@code true} if the item is a drink, {@code false} otherwise.
     */
    boolean isDrink();

    /**
     * Get the food points.
     *
     * @return The food points
     */
    int getFoodPoints();

    /**
     * Get the saturation points.
     *
     * @return The saturation points
     */
    float getSaturationPoints();
}
