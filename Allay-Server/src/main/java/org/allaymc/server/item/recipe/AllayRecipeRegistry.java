package org.allaymc.server.item.recipe;

import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.item.recipe.RecipeRegistry;
import org.allaymc.api.item.recipe.ShapedRecipe;

import java.util.HashMap;
import java.util.Map;

/**
 * Allay Project 2023/11/25
 *
 * @author daoge_cmd
 */
public class AllayRecipeRegistry implements RecipeRegistry {
    private Map<Identifier, ShapedRecipe> shapedRecipes = new HashMap<>();

    public void registerVanillaRecipes() {
        // TODO
    }

    @Override
    public void registerShaped(ShapedRecipe recipe) {
        shapedRecipes.put(recipe.getIdentifier(), recipe);
    }

    @Override
    public ShapedRecipe getShapedRecipe(Identifier identifier) {
        return shapedRecipes.get(identifier);
    }
}
