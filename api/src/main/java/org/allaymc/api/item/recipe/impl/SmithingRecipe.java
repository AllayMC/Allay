package org.allaymc.api.item.recipe.impl;

import lombok.Getter;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.descriptor.ItemDescriptor;
import org.allaymc.api.item.recipe.input.RecipeInput;
import org.allaymc.api.item.recipe.input.SmithingRecipeInput;
import org.allaymc.api.utils.identifier.Identifier;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.RecipeData;

import java.util.UUID;

/**
 * @author IWareQ
 */
@Getter
public abstract class SmithingRecipe extends CraftingRecipe {
    protected ItemDescriptor template;
    protected ItemDescriptor base;
    protected ItemDescriptor addition;

    protected SmithingRecipe(Identifier identifier, ItemDescriptor template, ItemDescriptor base, ItemDescriptor addition, ItemStack[] outputs, String tag, UUID uuid, int priority) {
        super(identifier, outputs, tag, uuid, priority);
        this.template = template;
        this.base = base;
        this.addition = addition;
        this.networkRecipeDataCache = buildNetworkRecipeData();
    }

    @Override
    public boolean match(RecipeInput input) {
        if (input instanceof SmithingRecipeInput recipeInput) {
            return template.match(recipeInput.getTemplate()) &&
                   base.match(recipeInput.getBase()) &&
                   addition.match(recipeInput.getAddition());
        }

        return false;
    }

    protected abstract RecipeData buildNetworkRecipeData();
}
