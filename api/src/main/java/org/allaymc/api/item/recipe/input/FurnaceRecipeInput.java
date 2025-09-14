package org.allaymc.api.item.recipe.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.item.ItemStack;

/**
 * Represents a furnace input.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class FurnaceRecipeInput implements RecipeInput {
    protected ItemStack ingredient;
    protected String tag;
}
