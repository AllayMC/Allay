package org.allaymc.api.item.recipe.input;

import org.allaymc.api.item.ItemStack;

/**
 * Represents the input for a smithing recipe.
 *
 * @param template the template item used for the smithing operation
 * @param base     the base item to be upgraded or modified
 * @param addition the additional item required for the smithing operation
 * @author IWareQ | daoge_cmd
 */
public record SmithingRecipeInput(ItemStack template, ItemStack base, ItemStack addition) implements RecipeInput {
}
