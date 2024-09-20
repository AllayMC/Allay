package org.allaymc.api.item.recipe;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.recipe.input.Input;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.CraftingDataType;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.RecipeData;

/**
 * Represents a recipe.
 *
 * @author daoge_cmd
 */
public interface Recipe {
    /**
     * Check if the input matches the recipe.
     *
     * @param input the input to check.
     * @return {@code true} if the input matches the recipe, {@code false} otherwise.
     */
    boolean match(Input input);

    /**
     * Get the outputs of this recipe.
     *
     * @return the outputs of this recipe.
     */
    ItemStack[] getOutputs();

    /**
     * Get the type of this recipe.
     *
     * @return the type of this recipe.
     */
    CraftingDataType getType();

    /**
     * Create the network data of this recipe.
     *
     * @return the network data of this recipe.
     */
    RecipeData toNetworkRecipeData();
}
