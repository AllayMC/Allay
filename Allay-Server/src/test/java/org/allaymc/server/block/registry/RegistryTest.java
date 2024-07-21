package org.allaymc.server.block.registry;

import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.registry.Registries;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Allay Project 2023/4/8
 *
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
public class RegistryTest {
    @Test
    void testBlockTypeRegistry() {
        for (var id : VanillaBlockId.values()) {
            assertNotNull(Registries.BLOCKS.get(id.getIdentifier()));
        }
    }

    @Test
    void testBlockAttributeRegistry() {
        //Special case
        assertNotNull(Registries.VANILLA_BLOCK_STATE_DATA.get(VanillaBlockId.UNKNOWN).get(-2));
    }

    @Test
    void testItemAttributeRegistry() {
        for (var id : VanillaItemId.values()) {
            assertNotNull(Registries.VANILLA_ITEM_DATA.get(id));
        }
    }

    @Test
    void testItemTypeRegistry() {
        for (var id : VanillaItemId.values()) {
            assertNotNull(Registries.ITEMS.get(id.getIdentifier()));
        }
    }
}
