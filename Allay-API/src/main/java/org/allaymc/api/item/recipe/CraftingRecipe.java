package org.allaymc.api.item.recipe;

import lombok.Getter;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.utils.Identifier;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.RecipeUnlockingRequirement;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.RecipeData;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Allay Project 2023/11/25
 *
 * @author daoge_cmd | CoolLoong
 */
public abstract class CraftingRecipe implements Recipe, TaggedRecipe, UniqueRecipe, IdentifiedRecipe, NetworkRecipe {
    protected Identifier identifier;
    protected ItemStack[] outputs;
    protected String tag;
    protected int networkId;
    protected UUID uuid;
    // 配方优先级，当出现多个匹配配方时客户端需要根据优先级决定使用哪个配方
    // 服务端实现并不需要用到此参数，但是客户端需要
    @Getter
    protected int priority;
    protected RecipeData networkRecipeDataCache;
    protected RecipeUnlockingRequirement requirement;

    protected CraftingRecipe(Identifier identifier, ItemStack[] outputs, String tag, UUID uuid, int priority, RecipeUnlockingRequirement requirement) {
        this.identifier = identifier;
        this.outputs = outputs;
        this.tag = tag;
        this.networkId = NetworkRecipe.assignNetworkId();
        this.uuid = uuid;
        this.priority = priority;
        this.requirement = requirement;
    }


    @Override
    public Identifier getIdentifier() {
        return identifier;
    }

    @Override
    public ItemStack[] getOutputs() {
        return outputs;
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public int getNetworkId() {
        return networkId;
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    @Override
    public RecipeData toNetworkRecipeData() {
        return networkRecipeDataCache;
    }

    public RecipeUnlockingRequirement getRequirement() {
        return requirement;
    }

    protected List<ItemData> buildNetworkOutputs() {
        List<ItemData> results = new ArrayList<>();
        for (var output : outputs) {
            results.add(output.toNetworkItemData());
        }
        return results;
    }
}
