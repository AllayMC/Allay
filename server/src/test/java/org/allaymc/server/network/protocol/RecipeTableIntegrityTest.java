package org.allaymc.server.network.protocol;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import org.allaymc.api.item.recipe.FurnaceRecipe;
import org.allaymc.api.item.recipe.Recipe;
import org.allaymc.api.registry.Registries;
import org.allaymc.server.network.protocol.v975.Protocol_v975;
import org.allaymc.testutils.AllayTestExtension;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.FurnaceRecipeData;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.IdentifiableRecipeData;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.NetworkRecipeData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * {@code RecipeTableIntegrityTest} checks recipe network IDs against payload
 * identity and reverse-index integrity.
 */
@ExtendWith(AllayTestExtension.class)
class RecipeTableIntegrityTest {

    /**
     * Collects every network recipe ID from legacy and modern tables. IDs must
     * be positive and unique, and their exact set must match the reverse-index
     * keys.
     */
    @Test
    void reverseIndexExactlyMatchesWireNetworkRecipes() {
        for (int version : List.of(944, 975)) {
            var table = protocol(version).getData().recipeTable();
            var wireIds = new HashSet<Integer>();
            for (var recipe : table.encodedRecipes()) {
                if (!(recipe instanceof NetworkRecipeData networkRecipe)) {
                    continue;
                }

                assertTrue(networkRecipe.getNetId() > 0);
                assertTrue(wireIds.add(networkRecipe.getNetId()), "duplicate wire recipe network ID");
                assertNotNull(table.recipesByNetworkId().get(networkRecipe.getNetId()));
            }

            assertEquals(wireIds, new HashSet<>(table.recipesByNetworkId().keySet()));
        }
    }

    /**
     * Compares furnace representation before and after the modern recipe
     * format. Legacy furnace payloads must remain outside the network index,
     * while modern furnace recipes are indexed.
     */
    @Test
    void legacyFurnacePayloadsDoNotCreatePhantomNetworkIds() {
        var legacyTable = protocol(944).getData().recipeTable();
        assertTrue(legacyTable.encodedRecipes().stream().anyMatch(FurnaceRecipeData.class::isInstance));
        assertFalse(legacyTable.recipesByNetworkId().values().stream().anyMatch(FurnaceRecipe.class::isInstance));

        var modernTable = protocol(975).getData().recipeTable();
        assertTrue(modernTable.recipesByNetworkId().values().stream().anyMatch(FurnaceRecipe.class::isInstance));
    }

    /**
     * Constructs tables with an unused index entry, a missing entry, and a
     * {@code null} recipe value. Each malformed relationship must throw
     * {@code IllegalArgumentException} during table construction.
     */
    @Test
    void recipeTableRejectsMissingAndPhantomReverseMappings() {
        var legacyTable = protocol(944).getData().recipeTable();
        var legacyFurnace = legacyTable.encodedRecipes().stream()
                .filter(FurnaceRecipeData.class::isInstance)
                .map(FurnaceRecipeData.class::cast)
                .findFirst()
                .orElseThrow();
        var phantomIndex = new Int2ObjectOpenHashMap<Recipe>();
        phantomIndex.put(1, legacyTable.recipesByNetworkId().values().iterator().next());
        assertThrows(
                IllegalArgumentException.class,
                () -> new RecipeTable(List.of(legacyFurnace), List.of(), phantomIndex)
        );

        var networkRecipe = protocol(975).getData().recipeTable().encodedRecipes().stream()
                .filter(NetworkRecipeData.class::isInstance)
                .map(NetworkRecipeData.class::cast)
                .findFirst()
                .orElseThrow();
        assertThrows(
                IllegalArgumentException.class,
                () -> new RecipeTable(
                        List.of(networkRecipe),
                        List.of(),
                        new Int2ObjectOpenHashMap<Recipe>()
                )
        );

        var nullIndex = new Int2ObjectOpenHashMap<Recipe>();
        nullIndex.put(networkRecipe.getNetId(), null);
        assertThrows(
                IllegalArgumentException.class,
                () -> new RecipeTable(List.of(networkRecipe), List.of(), nullIndex)
        );
    }

    /**
     * Swaps the indexed recipes beneath two identifiable wire recipe IDs.
     * Construction must reject the table even though both expected ID keys are
     * still present.
     */
    @Test
    void recipeTableRejectsSwappedReverseMappings() {
        var table = protocol(975).getData().recipeTable();
        var encoded = table.encodedRecipes().stream()
                .filter(IdentifiableRecipeData.class::isInstance)
                .filter(NetworkRecipeData.class::isInstance)
                .limit(2)
                .toList();
        assertEquals(2, encoded.size());

        var first = (NetworkRecipeData) encoded.get(0);
        var second = (NetworkRecipeData) encoded.get(1);
        // Keep the ID set unchanged while exchanging values, isolating identity
        // validation from the separate checks for missing or phantom mappings.
        var swapped = new Int2ObjectOpenHashMap<Recipe>();
        swapped.put(first.getNetId(), table.recipesByNetworkId().get(second.getNetId()));
        swapped.put(second.getNetId(), table.recipesByNetworkId().get(first.getNetId()));

        assertThrows(
                IllegalArgumentException.class,
                () -> new RecipeTable(encoded, List.of(), swapped)
        );
    }

    /**
     * Initializes two fresh v975 protocol tables from the shared recipe
     * registry. Their indexed identifiers must follow sorted source order and
     * retain identical network-ID assignments across both initializations.
     */
    @Test
    void recipeSourceAndRepeatedTablesUseStableIdentifierOrder() {
        var sourceIdentifiers = Registries.RECIPES.getContent().values().stream()
                .map(recipe -> recipe.getIdentifier().toString())
                .sorted()
                .toList();

        var firstProtocol = new Protocol_v975();
        firstProtocol.initialize();
        var secondProtocol = new Protocol_v975();
        secondProtocol.initialize();
        var first = firstProtocol.getData().recipeTable();
        var second = secondProtocol.getData().recipeTable();
        var indexedIdentifiers = indexedIdentifiers(first).entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .toList();
        assertEquals(indexedIdentifiers.stream().sorted().toList(), indexedIdentifiers);
        assertTrue(sourceIdentifiers.containsAll(indexedIdentifiers));
        assertEquals(indexedIdentifiers(first), indexedIdentifiers(second));
    }

    /**
     * Creates a network-ID-to-identifier map from the table's indexed recipes.
     */
    private static Map<Integer, String> indexedIdentifiers(RecipeTable table) {
        var identifiers = new HashMap<Integer, String>();
        table.recipesByNetworkId().forEach(
                (networkId, recipe) -> identifiers.put(networkId, recipe.getIdentifier().toString())
        );
        return identifiers;
    }

    /**
     * Returns the initialized international protocol for the requested test
     * version. A missing default-registry entry fails immediately so table
     * assertions operate only on an explicitly selected supported version.
     */
    private static Protocol protocol(int version) {
        var protocol = ProtocolRegistry.getDefault().resolve(ClientVariant.INTERNATIONAL, version);
        assertNotNull(protocol);
        return protocol;
    }
}
