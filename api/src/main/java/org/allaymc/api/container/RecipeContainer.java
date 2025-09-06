package org.allaymc.api.container;

import org.allaymc.api.item.recipe.input.RecipeInput;

/**
 * RecipeContainer represents a type of container that is used to craft item.
 *
 * @author IWareQ
 */
public interface RecipeContainer {
    /**
     * Creates a {@link RecipeInput} instance for the current container.
     *
     * @return a new instance of {@link RecipeInput} representing the input for the recipe
     */
    RecipeInput createRecipeInput();
}
