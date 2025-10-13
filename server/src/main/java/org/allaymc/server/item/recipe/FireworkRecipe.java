package org.allaymc.server.item.recipe;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.recipe.input.RecipeInput;

/**
 * @author daoge_cmd
 */
public final class FireworkRecipe extends ComplexRecipe {

    public static final FireworkRecipe INSTANCE = new FireworkRecipe();

    private FireworkRecipe() {
        super(ComplexRecipeIds.FIREWORKS);
    }

    @Override
    public boolean match(RecipeInput input) {
        // TODO
        return false;
    }

    @Override
    public ItemStack[] getOutputs(RecipeInput input) {
        // TODO
        return null;
    }
}
