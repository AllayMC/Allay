package org.allaymc.api.item.recipe.impl;

import lombok.Builder;
import lombok.Getter;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.descriptor.ItemDescriptor;
import org.allaymc.api.utils.Identifier;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.CraftingDataType;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.RecipeData;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.SmithingTransformRecipeData;

import java.util.UUID;

/**
 * @author IWareQ
 */
@Getter
public class SmithingTransformRecipe extends SmithingRecipe {
    @Builder
    protected SmithingTransformRecipe(Identifier identifier, ItemDescriptor template, ItemDescriptor base, ItemDescriptor addition, ItemStack[] outputs, String tag, UUID uuid, int priority) {
        super(identifier, template, base, addition, outputs, tag, uuid, priority);
    }

    @Override
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

    @Override
    public CraftingDataType getType() {
        return CraftingDataType.SMITHING_TRANSFORM;
    }
}
