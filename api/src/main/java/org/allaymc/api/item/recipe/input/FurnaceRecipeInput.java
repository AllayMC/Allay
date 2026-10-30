package org.allaymc.api.item.recipe.input;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.recipe.FurnaceRecipe;

/**
 * {@code FurnaceRecipeInput} represents an ingredient stack presented to a
 * furnace recipe with the processing block's category. The record retains the
 * stack directly and performs no validation; {@code FurnaceRecipe.match}
 * combines the supplied category with its own ingredient descriptor.
 *
 * @param ingredient shared ingredient stack presented for matching
 * @param type furnace category supplied to the recipe matcher
 *
 * @author daoge_cmd
 */
public record FurnaceRecipeInput(ItemStack ingredient, FurnaceRecipe.Type type) implements RecipeInput {
}
