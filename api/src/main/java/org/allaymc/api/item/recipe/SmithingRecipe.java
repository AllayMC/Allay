package org.allaymc.api.item.recipe;

import lombok.Getter;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.recipe.descriptor.ItemDescriptor;
import org.allaymc.api.item.recipe.input.RecipeInput;
import org.allaymc.api.item.recipe.input.SmithingRecipeInput;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * @author IWareQ
 */
@Getter
public abstract class SmithingRecipe extends Recipe {
    protected ItemDescriptor template;
    protected ItemDescriptor base;
    protected ItemDescriptor addition;

    public SmithingRecipe(Identifier identifier, ItemStack[] outputs, int priority, ItemDescriptor template, ItemDescriptor base, ItemDescriptor addition) {
        super(identifier, outputs, priority);
        this.template = template;
        this.base = base;
        this.addition = addition;
    }

    @Override
    public boolean match(RecipeInput input) {
        if (input instanceof SmithingRecipeInput(ItemStack template1, ItemStack base1, ItemStack addition1)) {
            return template.match(template1) && base.match(base1) && addition.match(addition1);
        }

        return false;
    }
}
