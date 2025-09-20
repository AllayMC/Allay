package org.allaymc.api.item.recipe.input;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.recipe.FurnaceRecipe;

/**
 * Represents a furnace input.
 *
 * @author daoge_cmd
 */
public record FurnaceRecipeInput(ItemStack ingredient, FurnaceRecipe.Type type) implements RecipeInput {
}
