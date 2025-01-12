package org.allaymc.api.item.recipe.impl;

import org.allaymc.api.item.recipe.Recipe;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.CraftingDataType;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.RecipeData;

/**
 * @author IWareQ
 */
public interface BaseRecipe extends Recipe<RecipeData> {
    /**
     * Get the type of this recipe.
     *
     * @return the type of this recipe.
     */
    CraftingDataType getType();
}
