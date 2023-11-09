package org.allaymc.server.item.type;

import org.allaymc.api.item.registry.CreativeItemRegistry;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Allay Project 2023/7/22
 *
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
public class CreativeItemRegistryTest {
    @Test
    void testCreativeItemRegistry() {
        assertFalse(CreativeItemRegistry.getRegistry().getContent().isEmpty());
        for (var itemStack : CreativeItemRegistry.getRegistry().getContent().values()) {
            assertEquals(1, itemStack.getCount());
        }
    }
}
