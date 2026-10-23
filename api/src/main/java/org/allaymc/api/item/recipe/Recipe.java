package org.allaymc.api.item.recipe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.recipe.input.RecipeInput;
import org.allaymc.api.utils.identifier.Identified;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code Recipe} associates a recipe identifier and priority with output stacks
 * and an input-matching contract. Concrete subclasses decide which input
 * representation they accept. Construction retains the supplied output array
 * and its mutable stacks, and generated accessors expose that same storage
 * without copying it.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public abstract class Recipe implements Identified {
    /**
     * Identifier used for recipe registration and lookup.
     */
    protected final Identifier identifier;
    /**
     * {@code outputs} retains the supplied array and mutable stacks without
     * copying. {@code getOutputs()} exposes this storage directly; dynamic
     * recipes may instead use {@code null} and override output access.
     */
    protected final ItemStack[] outputs;
    /**
     * Recipe selection priority included in network recipe data.
     */
    protected final int priority;

    /**
     * Returns whether the supplied input satisfies this recipe's matching
     * rules. Concrete implementations select their supported input subtype;
     * dynamic recipes may construct outputs or update input metadata while
     * determining whether an input matches.
     */
    public abstract boolean match(RecipeInput input);

    /**
     * Returns the first stack in the retained output array without copying it.
     * Callers must ensure that {@code outputs} is non-{@code null} and
     * nonempty; this base implementation otherwise fails during array access.
     */
    public ItemStack getOutput() {
        return outputs[0];
    }
}
