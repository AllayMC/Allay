package org.allaymc.api.item.recipe.impl;

import lombok.Builder;
import lombok.Getter;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.descriptor.ItemDescriptor;
import org.allaymc.api.item.recipe.input.RecipeInput;
import org.allaymc.api.item.recipe.input.SmithingTransformRecipeInput;
import org.allaymc.api.utils.Identifier;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.CraftingDataType;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.RecipeData;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.SmithingTransformRecipeData;

import java.util.UUID;

/**
 * @author IWareQ
 */
@Getter
public class SmithingTransformRecipe extends CraftingRecipe {
    protected ItemDescriptor template;
    protected ItemDescriptor base;
    protected ItemDescriptor addition;

    @Builder
    protected SmithingTransformRecipe(Identifier identifier, ItemDescriptor template, ItemDescriptor base, ItemDescriptor addition, ItemStack[] outputs, String tag, UUID uuid, int priority) {
        super(identifier, outputs, tag, uuid, priority);
        this.template = template;
        this.base = base;
        this.addition = addition;
        this.networkRecipeDataCache = buildNetworkRecipeData();
    }

    @Override
    public boolean match(RecipeInput input) {
        if (input instanceof SmithingTransformRecipeInput recipeInput) {
            return recipeInput.getTemplate().equals(template) &&
                   recipeInput.getBase().equals(base) &&
                   recipeInput.getAddition().equals(addition);
        }

        return false;
    }

    @Override
    public CraftingDataType getType() {
        return CraftingDataType.SMITHING_TRANSFORM;
    }

    protected RecipeData buildNetworkRecipeData() {
        return SmithingTransformRecipeData.of(
                identifier.toString(),
                template.toNetworkWithCount(),
                base.toNetworkWithCount(),
                addition.toNetworkWithCount(),
                outputs[0].toNetworkItemData(),
                tag,
                networkId
        );
    }
}
