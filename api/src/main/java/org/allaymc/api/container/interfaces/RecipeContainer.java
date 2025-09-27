package org.allaymc.api.container.interfaces;

import org.allaymc.api.container.Container;
import org.allaymc.api.item.recipe.input.RecipeInput;

/**
 * RecipeContainer represents a type of container that is used to craft item.
 *
 * @author IWareQ
 */
public interface RecipeContainer extends Container {
    /**
     * Creates a {@link RecipeInput} instance for the current container.
     *
     * @return a new instance of {@link RecipeInput} representing the input for the recipe
     */
    RecipeInput createRecipeInput();
}
