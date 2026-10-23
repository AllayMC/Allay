package org.allaymc.api.item.recipe.input;

import org.allaymc.api.item.ItemStack;

/**
 * {@code PotionRecipeInput} represents the primary ingredient and reagent
 * presented to a brewing recipe. The record retains both mutable stacks without
 * validation or copying; {@code PotionRecipe.match} compares their item types
 * and metadata rather than consuming them.
 *
 * @param ingredient shared primary brewing ingredient stack
 * @param reagent shared secondary brewing reagent stack
 *
 * @author IWareQ | daoge_cmd
 */
public record PotionRecipeInput(ItemStack ingredient, ItemStack reagent) implements RecipeInput {
}
