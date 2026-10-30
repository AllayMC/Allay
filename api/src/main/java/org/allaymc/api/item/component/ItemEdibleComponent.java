package org.allaymc.api.item.component;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.jetbrains.annotations.ApiStatus;

/**
 * {@code ItemEdibleComponent} describes item consumption, including nutrition,
 * eating time, and drink behavior. The server invokes {@code onEaten} after
 * its use-time and consumption-event checks, while the remaining properties
 * determine whether use may begin and how long it takes.
 *
 * @author IWareQ
 */
public interface ItemEdibleComponent extends ItemComponent {
    /**
     * Applies this item's consumption effects to the supplied player. The
     * server calls this callback after the eating event succeeds; the
     * surrounding use handler separately attempts to consume the held item.
     */
    @ApiStatus.OverrideOnly
    void onEaten(EntityPlayer player);

    /**
     * Returns whether this item may be consumed without hunger. A {@code true}
     * result bypasses the player's ordinary eating-eligibility check, as
     * needed for drinks and some special foods.
     */
    boolean canBeAlwaysEaten();

    /**
     * Returns the minimum held-use duration in game ticks.
     */
    int getEatingTime();

    /**
     * Returns whether this item uses the drink consumption behavior.
     */
    boolean isDrink();

    /**
     * Returns the food points supplied when the item is consumed.
     */
    int getFoodPoints();

    /**
     * Returns the saturation points supplied when the item is consumed.
     */
    float getSaturationPoints();
}
