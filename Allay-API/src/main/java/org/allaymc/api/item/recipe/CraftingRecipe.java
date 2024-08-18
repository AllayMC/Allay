package org.allaymc.api.item.recipe;

import lombok.AccessLevel;
import lombok.Getter;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemBaseComponent;
import org.allaymc.api.utils.Identifier;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.RecipeData;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Allay Project 2023/11/25
 *
 * @author daoge_cmd
 */
@Getter
public abstract class CraftingRecipe implements Recipe, TaggedRecipe, UniqueRecipe, IdentifiedRecipe, NetworkRecipe {
    protected Identifier identifier;
    protected ItemStack[] outputs;
    protected String tag;
    protected int networkId;
    protected UUID uuid;
    // Recipe priority: when multiple matching recipes are available, the client decides which one to use based on this priority
    // This parameter is not required for server-side implementation but is necessary for clients
    protected int priority;
    @Getter(AccessLevel.NONE)
    protected RecipeData networkRecipeDataCache;

    protected CraftingRecipe(Identifier identifier, ItemStack[] outputs, String tag, UUID uuid, int priority) {
        this.identifier = identifier;
        this.outputs = outputs;
        this.tag = tag;
        this.networkId = NetworkRecipe.assignNetworkId();
        this.uuid = uuid;
        this.priority = priority;
    }

    @Override
    public RecipeData toNetworkRecipeData() {
        return networkRecipeDataCache;
    }

    protected List<ItemData> buildNetworkOutputs() {
        return Arrays.stream(outputs).map(ItemBaseComponent::toNetworkItemData).toList();
    }
}
