package org.allaymc.server.registry;

import org.allaymc.api.registry.Registries;
import org.allaymc.server.block.data.BlockId;
import org.allaymc.server.item.data.ItemId;
import org.allaymc.server.world.biome.BiomeId;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * {@code RegistryTest} checks that shared bootstrap fixtures populate the
 * generated vanilla catalogs.
 *
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
public class RegistryTest {
    /**
     * Looks up every generated block identifier in the populated runtime
     * registry. Each identifier must resolve to a block type after the shared
     * fixture completes registry initialization.
     */
    @Test
    void testBlockTypeRegistry() {
        for (var id : BlockId.values()) {
            assertNotNull(Registries.BLOCKS.get(id.getIdentifier()));
        }
    }

    /**
     * Looks up metadata for every generated block identifier after bootstrap.
     * Each block must have a state-data map, and the unknown block must also
     * expose the special state hash used by the fixture.
     */
    @Test
    void testBlockStateDataRegistry() {
        for (var id : BlockId.values()) {
            assertNotNull(InternalRegistries.BLOCK_STATE_DATA.get(id));
        }
        // Unknown block data narrows unsigned 4294967294 to signed -2.
        assertNotNull(InternalRegistries.BLOCK_STATE_DATA.get(BlockId.UNKNOWN).get(-2));
    }

    /**
     * Checks the item metadata registry against every generated item
     * identifier. Bootstrap must provide a nonnull data record for each
     * identifier in the current generated catalog.
     */
    @Test
    void testItemDataRegistry() {
        for (var id : ItemId.values()) {
            assertNotNull(InternalRegistries.ITEM_DATA.get(id));
        }
    }

    /**
     * Looks up every generated item identifier in the runtime type registry.
     * Each identifier must resolve to an item type after the shared fixture
     * completes registry initialization.
     */
    @Test
    void testItemTypeRegistry() {
        for (var id : ItemId.values()) {
            assertNotNull(Registries.ITEMS.get(id.getIdentifier()));
        }
    }

    /**
     * Checks the biome metadata registry against every generated biome
     * identifier. Bootstrap must provide a nonnull environmental data record
     * for each biome represented by the current generated catalog.
     */
    @Test
    void testBiomeDataRegistry() {
        for (var id : BiomeId.values()) {
            assertNotNull(InternalRegistries.BIOME_DATA.get(id));
        }
    }

    /**
     * Resolves every generated biome through the textual-identifier index.
     * Bootstrap must populate that second lookup path with a nonnull biome type
     * for every entry in the generated catalog.
     */
    @Test
    void testBiomeRegistry() {
        for (var id : BiomeId.values()) {
            assertNotNull(Registries.BIOMES.getByK2(id.getIdentifier()));
        }
    }
}
