package org.allaymc.server.block.registry;

import org.allaymc.api.block.data.BlockId;
import org.allaymc.api.item.data.ItemId;
import org.allaymc.api.registry.Registries;
import org.allaymc.server.registry.InternalRegistries;
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
    void testBlockAttributeRegistry() {
        //Special case
        assertNotNull(InternalRegistries.BLOCK_STATE_DATA.get(BlockId.UNKNOWN).get(-2));
    }

    @Test
    void testItemAttributeRegistry() {
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
}
