package org.allaymc.api.item.recipe.input;

import org.allaymc.api.item.ItemStack;

/**
 * Represents the input for a potion recipe.
 *
 * @param ingredient the primary item required for the potion recipe
 * @param reagent    the secondary item used to modify or enhance the potion
 * @author IWareQ | daoge_cmd
 */
public record PotionRecipeInput(ItemStack ingredient, ItemStack reagent) implements RecipeInput {
}
