package org.allaymc.server.block.registry;

import org.allaymc.api.block.registry.VanillaBlockAttributeRegistry;
import org.allaymc.api.block.registry.BlockTypeRegistry;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.registry.VanillaItemAttributeRegistry;
import org.allaymc.api.item.registry.ItemTypeRegistry;
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
            assertNotNull(BlockTypeRegistry.getRegistry().get(id.getIdentifier()));
        }
    }

    @Test
    void testBlockAttributeRegistry() {
        //Special case
        assertNotNull(VanillaBlockAttributeRegistry.getRegistry().get(VanillaBlockId.UNKNOWN).get(-2));
    }

    @Test
    void testItemAttributeRegistry() {
        for (var id : VanillaItemId.values()) {
            assertNotNull(VanillaItemAttributeRegistry.getRegistry().get(id));
        }
    }

    @Test
    void testItemTypeRegistry() {
        for (var id : VanillaItemId.values()) {
            assertNotNull(ItemTypeRegistry.getRegistry().get(id.getIdentifier()));
        }
    }
}
