package org.allaymc.api.item.recipe;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.recipe.input.RecipeInput;

/**
 * Represents a recipe.
 *
 * @author daoge_cmd
 */
public interface Recipe<T> {
    /**
     * Check if the input matches the recipe.
     *
     * @param input the input to check
     *
     * @return {@code true} if the input matches the recipe, {@code false} otherwise.
     */
    boolean match(RecipeInput input);

    /**
     * Get the outputs of this recipe.
     *
     * @return the outputs of this recipe
     */
    ItemStack[] getOutputs();

    /**
     * Create the network data of this recipe.
     *
     * @return the network data of this recipe
     */
    T toNetworkData();
}
