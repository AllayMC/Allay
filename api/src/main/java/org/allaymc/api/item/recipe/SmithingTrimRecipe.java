package org.allaymc.api.item.recipe;

import lombok.Getter;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.recipe.descriptor.ItemDescriptor;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code SmithingTrimRecipe} identifies a smithing trimming recipe with
 * template, base, and addition requirements. Input matching delegates to
 * {@code SmithingRecipe}, and the supplied outputs describe the result; this
 * class adds the concrete recipe kind used by downstream recipe handling.
 *
 * @author IWareQ
 */
@Getter
public class SmithingTrimRecipe extends SmithingRecipe {
    /**
     * Creates a smithing trimming recipe with the supplied identity, priority,
     * and descriptors. The superclass retains the output array and descriptors
     * directly, so callers coordinate later changes with recipe consumers.
     */
    public SmithingTrimRecipe(Identifier identifier, ItemStack[] outputs, int priority, ItemDescriptor template, ItemDescriptor base, ItemDescriptor addition) {
        super(identifier, outputs, priority, template, base, addition);
    }
}
