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
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
public class RegistryTest {
    @Test
    void testBlockTypeRegistry() {
        for (var id : BlockId.values()) {
            assertNotNull(Registries.BLOCKS.get(id.getIdentifier()));
        }
    }

    @Test
    void testBlockStateDataRegistry() {
        for (var id : BlockId.values()) {
            assertNotNull(InternalRegistries.BLOCK_STATE_DATA.get(id));
        }
        //Special case
        assertNotNull(InternalRegistries.BLOCK_STATE_DATA.get(BlockId.UNKNOWN).get(-2));
    }

    @Test
    void testItemDataRegistry() {
        for (var id : ItemId.values()) {
            assertNotNull(InternalRegistries.ITEM_DATA.get(id));
        }
    }

    @Test
    void testItemTypeRegistry() {
        for (var id : ItemId.values()) {
            assertNotNull(Registries.ITEMS.get(id.getIdentifier()));
        }
    }

    @Test
    void testBiomeDataRegistry() {
        for (var id : BiomeId.values()) {
            assertNotNull(InternalRegistries.BIOME_DATA.get(id));
        }
    }

    @Test
    void testBiomeRegistry() {
        for (var id : BiomeId.values()) {
            assertNotNull(Registries.BIOMES.getByK2(id.getIdentifier()));
        }
    }
}
