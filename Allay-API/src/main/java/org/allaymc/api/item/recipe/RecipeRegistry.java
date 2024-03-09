package org.allaymc.api.item.recipe;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.utils.Identifier;
import org.cloudburstmc.protocol.bedrock.packet.CraftingDataPacket;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Set;

/**
 * Allay Project 2023/11/25
 *
 * @author daoge_cmd
 */
public interface RecipeRegistry {
    ApiInstanceHolder<RecipeRegistry> REGISTRY = ApiInstanceHolder.create();

    static RecipeRegistry getRegistry() {
        return REGISTRY.get();
    }

    @UnmodifiableView
    Set<Recipe> getRecipes();

    NetworkRecipe getRecipeByNetworkId(int networkId);

    void registerShaped(ShapedRecipe recipe);

    ShapedRecipe getShapedRecipe(Identifier identifier);

    void registerShapeless(ShapelessRecipe recipe);

    ShapelessRecipe getShapelessRecipe(Identifier identifier);

    // TODO: more recipe type

    CraftingDataPacket getCraftingDataPacket();
}
