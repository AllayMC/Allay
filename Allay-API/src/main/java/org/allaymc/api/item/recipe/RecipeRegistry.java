package org.allaymc.api.item.recipe;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.identifier.Identifier;

/**
 * Allay Project 2023/11/25
 *
 * @author daoge_cmd
 */
public interface RecipeRegistry {
    ApiInstanceHolder<RecipeRegistry> REGISTRY = ApiInstanceHolder.of();

    static RecipeRegistry getRegistry() {
        return REGISTRY.get();
    }

    NetworkRecipe getRecipeByNetworkId(int networkId);

    void registerShaped(ShapedRecipe recipe);

    ShapedRecipe getShapedRecipe(Identifier identifier);

    void registerShapeless(ShapelessRecipe recipe);

    ShapelessRecipe getShapelessRecipe(Identifier identifier);

    // TODO: more recipe type
}
