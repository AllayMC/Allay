package org.allaymc.api.item.recipe;

import lombok.Getter;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.recipe.descriptor.ItemDescriptor;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code SmithingTransformRecipe} identifies a smithing transformation recipe
 * with template, base, and addition requirements. Input matching delegates to
 * {@code SmithingRecipe}, and the supplied outputs describe the result; this
 * class adds the concrete recipe kind used by downstream recipe handling.
 *
 * @author IWareQ
 */
@Getter
public class SmithingTransformRecipe extends SmithingRecipe {
    /**
     * Creates a smithing transformation recipe with the supplied identity,
     * priority, and descriptors. The superclass retains the output array and
     * descriptors directly, so callers coordinate later changes with recipe
     * consumers.
     */
    public SmithingTransformRecipe(Identifier identifier, ItemStack[] outputs, int priority, ItemDescriptor template, ItemDescriptor base, ItemDescriptor addition) {
        super(identifier, outputs, priority, template, base, addition);
    }
}
