package org.allaymc.api.item.recipe;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Represents a network recipe.
 *
 * @author daoge_cmd
 */
public interface NetworkRecipe extends Recipe {
    AtomicInteger NETWORK_ID_COUNTER = new AtomicInteger(1);

    static int assignNetworkId() {
        return NETWORK_ID_COUNTER.getAndIncrement();
    }

    /**
     * Get the unique network ID of the recipe, assigned using {@link #assignNetworkId()} internally.
     *
     * @return The unique network ID of the recipe, assigned internally.
     */
    int getNetworkId();
}
