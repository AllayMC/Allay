package org.allaymc.api.item.recipe;

import lombok.Getter;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.recipe.descriptor.ItemDescriptor;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * @author IWareQ
 */
@Getter
public class SmithingTrimRecipe extends SmithingRecipe {
    public SmithingTrimRecipe(Identifier identifier, ItemStack[] outputs, int priority, ItemDescriptor template, ItemDescriptor base, ItemDescriptor addition) {
        super(identifier, outputs, priority, template, base, addition);
    }
}
