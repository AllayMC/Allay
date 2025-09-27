package org.allaymc.api.item.recipe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.recipe.input.RecipeInput;
import org.allaymc.api.utils.identifier.Identified;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * Represents an abstract recipe used for constructing or crafting specific outputs.
 * A recipe is identified by a unique identifier and specifies its outputs, the block
 * type used for crafting, and a priority which influences recipe selection.
 * <p>
 * The recipe matching logic is defined by the match() method in concrete recipe implementations.
 * Each recipe type can implement its own matching rules for determining if a given input matches
 * the recipe requirements.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public abstract class Recipe implements Identified {
    /**
     * The identifier for this recipe.
     */
    protected final Identifier identifier;
    /**
     * The outputs of this recipe. Can be {@code null} if the recipe has no outputs.
     */
    protected final ItemStack[] outputs;
    /**
     * The priority of this recipe. Recipes with lower priority are preferred compared to recipes
     * with higher priority.
     */
    protected final int priority;

    /**
     * Checks if the input matches the recipe.
     *
     * @param input the input to check
     * @return {@code true} if the input matches the recipe, {@code false} otherwise.
     */
    public abstract boolean match(RecipeInput input);

    /**
     * Retrieves the primary output of the recipe.
     *
     * @return the first output {@link ItemStack} in the recipe's output array.
     */
    public ItemStack getOutput() {
        return outputs[0];
    }
}
