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
 * {@code RecipeTable} associates encoded recipe payloads with the server
 * recipes identified by their network IDs. Recipes without network IDs,
 * including legacy furnace payloads, remain in {@code encodedRecipes} without
 * occupying {@code recipesByNetworkId}. {@code potionMixes} remains separate
 * from the indexed crafting payloads.
 *
 * <p> The constructor compares the complete set of encoded network IDs with the
 * index keys before exposing the table. Positive, unique IDs prevent one client
 * selection from addressing multiple recipes. Identifiable payloads must match
 * their indexed recipe's identifier; UUID-only payloads must match a
 * {@code ComplexRecipe} with the same UUID. The constructor copies the lists
 * and index into unmodifiable containers, but their recipe and payload objects
 * remain shared.
 *
 * @param encodedRecipes recipe payloads sent to the client
 *
 * @param potionMixes potion-mixing payloads sent to the client
 *
 * @param recipesByNetworkId server recipes indexed by encoded network ID
 */
public record RecipeTable(
        List<RecipeData> encodedRecipes,
        List<PotionMixData> potionMixes,
        Int2ObjectMap<Recipe> recipesByNetworkId
) {
    /**
     * Creates a recipe table after checking payload and lookup identities.
     * Input containers and list elements must be nonnull, and encoded network
     * IDs must be positive, unique, and represented by exactly the same keys in
     * the copied index.
     *
     * <p> Each indexed value must be nonnull and match the identifier carried
     * by its payload. UUID-only payloads require a {@code ComplexRecipe} with
     * the matching UUID. Explicit ID-set and identity checks report
     * inconsistent associations with {@code IllegalArgumentException};
     * {@code null} input containers or list elements cause
     * {@code NullPointerException}. Payload identifiers and UUIDs must also be
     * nonnull because identity comparisons dereference them. Successful
     * construction retains element references within unmodifiable containers.
     */
    public RecipeTable {
        encodedRecipes = List.copyOf(Objects.requireNonNull(encodedRecipes, "encodedRecipes"));
        potionMixes = List.copyOf(Objects.requireNonNull(potionMixes, "potionMixes"));

        var recipes = new Int2ObjectOpenHashMap<Recipe>();
        recipes.putAll(Objects.requireNonNull(recipesByNetworkId, "recipesByNetworkId"));
        // Compare complete ID sets before inspecting identity so neither orphan
        // payloads nor unused lookup entries survive validation.
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
            // Identifiers take precedence over UUID checks because ordinary
            // identifiable crafting recipes also carry incidental UUIDs.
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
