package org.allaymc.api.item.recipe;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.recipe.input.Input;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.CraftingDataType;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.RecipeData;

/**
 * @author daoge_cmd
 */
public interface Recipe {
    /**
     * @param input Input
     *
     * @return Whether the input matches the recipe
     */
    boolean match(Input input);

    /**
     * @return The outputs of this recipe
     */
    ItemStack[] getOutputs();

    CraftingDataType getType();

    RecipeData toNetworkRecipeData();
}
