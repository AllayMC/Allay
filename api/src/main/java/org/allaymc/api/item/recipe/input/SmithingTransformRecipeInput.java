package org.allaymc.api.item.recipe.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.item.ItemStack;

/**
 * @author IWareQ
 */
@Getter
@AllArgsConstructor
public class SmithingTransformRecipeInput implements RecipeInput {
    private final ItemStack template;
    private final ItemStack base;
    private final ItemStack addition;
}
