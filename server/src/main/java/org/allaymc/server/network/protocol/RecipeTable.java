package org.allaymc.server.network.protocol;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import org.allaymc.api.item.recipe.Recipe;
import org.allaymc.server.item.recipe.ComplexRecipe;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.PotionMixData;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.IdentifiableRecipeData;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.NetworkRecipeData;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.RecipeData;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.UniqueCraftingData;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/**
 * Protocol-specific encoded recipes and their matching server-side network ID index.
 */
public record RecipeTable(
        List<RecipeData> encodedRecipes,
        List<PotionMixData> potionMixes,
        Int2ObjectMap<Recipe> recipesByNetworkId
) {
    public RecipeTable {
        encodedRecipes = List.copyOf(Objects.requireNonNull(encodedRecipes, "encodedRecipes"));
        potionMixes = List.copyOf(Objects.requireNonNull(potionMixes, "potionMixes"));

        var recipes = new Int2ObjectOpenHashMap<Recipe>();
        recipes.putAll(Objects.requireNonNull(recipesByNetworkId, "recipesByNetworkId"));
        var encodedNetworkIds = new HashSet<Integer>();
        for (var encodedRecipe : encodedRecipes) {
            if (!(encodedRecipe instanceof NetworkRecipeData networkRecipe)) {
                continue;
            }

            int networkId = networkRecipe.getNetId();
            if (networkId <= 0 || !encodedNetworkIds.add(networkId)) {
                throw new IllegalArgumentException("Invalid or duplicate recipe network ID: " + networkId);
            }
        }
        if (!encodedNetworkIds.equals(new HashSet<>(recipes.keySet()))) {
            throw new IllegalArgumentException("Recipe network IDs do not match the encoded recipe payloads");
        }
        for (int networkId : encodedNetworkIds) {
            var recipe = recipes.get(networkId);
            if (recipe == null) {
                throw new IllegalArgumentException("Recipe network ID maps to null: " + networkId);
            }
        }
        for (var encodedRecipe : encodedRecipes) {
            if (!(encodedRecipe instanceof NetworkRecipeData networkRecipe)) {
                continue;
            }

            var recipe = recipes.get(networkRecipe.getNetId());
            if (encodedRecipe instanceof IdentifiableRecipeData identifiable &&
                !identifiable.getId().equals(recipe.getIdentifier().toString())) {
                throw new IllegalArgumentException(
                        "Recipe network ID maps to a different identifier: " + networkRecipe.getNetId()
                );
            }
            if (encodedRecipe instanceof UniqueCraftingData unique &&
                !(encodedRecipe instanceof IdentifiableRecipeData) &&
                (!(recipe instanceof ComplexRecipe complex) || !unique.getUuid().equals(complex.getUuid()))) {
                throw new IllegalArgumentException(
                        "Recipe network ID maps to a different UUID: " + networkRecipe.getNetId()
                );
            }
        }
        recipesByNetworkId = Int2ObjectMaps.unmodifiable(recipes);
    }
}
