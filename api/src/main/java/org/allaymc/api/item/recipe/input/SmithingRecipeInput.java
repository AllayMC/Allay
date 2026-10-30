package org.allaymc.api.item.recipe.input;

import org.allaymc.api.item.ItemStack;

/**
 * {@code SmithingRecipeInput} represents the three ordered ingredient slots
 * presented to a smithing recipe. The record retains each stack without
 * validation or copying; {@code SmithingRecipe.match} applies the separate
 * template, base, and addition descriptors to their corresponding positions.
 *
 * @param template shared smithing template slot stack
 * @param base shared smithing base slot stack
 * @param addition shared smithing addition slot stack
 *
 * @author IWareQ | daoge_cmd
 */
public record SmithingRecipeInput(ItemStack template, ItemStack base, ItemStack addition) implements RecipeInput {
}
