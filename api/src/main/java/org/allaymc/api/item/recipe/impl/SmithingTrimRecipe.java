package org.allaymc.api.item.recipe.impl;

import lombok.Builder;
import lombok.Getter;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.descriptor.ItemDescriptor;
import org.allaymc.api.utils.identifier.Identifier;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.CraftingDataType;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.RecipeData;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.SmithingTrimRecipeData;

import java.util.UUID;

/**
 * @author IWareQ
 */
@Getter
public class SmithingTrimRecipe extends SmithingRecipe {
    @Builder
    protected SmithingTrimRecipe(Identifier identifier, ItemDescriptor template, ItemDescriptor base, ItemDescriptor addition, ItemStack[] outputs, String tag, UUID uuid, int priority) {
        super(identifier, template, base, addition, outputs, tag, uuid, priority);
    }

    @Override
    protected RecipeData buildNetworkRecipeData() {
        return SmithingTrimRecipeData.of(
                identifier.toString(),
                base.toNetworkWithCount(),
                addition.toNetworkWithCount(),
                template.toNetworkWithCount(),
                tag,
                networkId
        );
    }

    @Override
    public CraftingDataType getType() {
        return CraftingDataType.SMITHING_TRIM;
    }
}
