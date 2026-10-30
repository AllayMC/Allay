package org.allaymc.api.item.recipe;

import lombok.Getter;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.recipe.descriptor.ItemDescriptor;
import org.allaymc.api.item.recipe.input.RecipeInput;
import org.allaymc.api.item.recipe.input.SmithingRecipeInput;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code SmithingRecipe} defines smithing input matching through the
 * {@code template}, {@code base}, and {@code addition} descriptors.
 * {@code match} requires all three positions to pass their own descriptors and
 * does not consume stacks. Construction retains all descriptors and output
 * storage, which generated accessors expose directly.
 *
 * @author IWareQ
 */
@Getter
public abstract class SmithingRecipe extends Recipe {
    /**
     * Template-slot matching descriptor.
     */
    protected ItemDescriptor template;
    /**
     * Base-slot matching descriptor.
     */
    protected ItemDescriptor base;
    /**
     * Addition-slot matching descriptor.
     */
    protected ItemDescriptor addition;

    /**
     * Creates a smithing recipe retaining all supplied descriptors and output
     * storage. Callers provide usable descriptors for every position;
     * construction performs no copying or validation, and later descriptor
     * changes affect matching.
     */
    public SmithingRecipe(Identifier identifier, ItemStack[] outputs, int priority, ItemDescriptor template, ItemDescriptor base, ItemDescriptor addition) {
        super(identifier, outputs, priority);
        this.template = template;
        this.base = base;
        this.addition = addition;
    }

    /**
     * Returns whether a smithing input satisfies the template, base, and
     * addition descriptors. Every position must match independently; other
     * input representations return {@code false}, and this check does not
     * consume any supplied stack.
     */
    @Override
    public boolean match(RecipeInput input) {
        if (input instanceof SmithingRecipeInput(ItemStack template1, ItemStack base1, ItemStack addition1)) {
            return template.match(template1) && base.match(base1) && addition.match(addition1);
        }

        return false;
    }
}
