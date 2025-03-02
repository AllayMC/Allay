package org.allaymc.server.item.type;

import org.allaymc.api.registry.Registries;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
public class CreativeItemRegistryTest {
    @Test
    void testCreativeItemRegistry() {
        assertFalse(Registries.CREATIVE_ITEMS.getEntries().isEmpty());
        for (var entry : Registries.CREATIVE_ITEMS.getEntries()) {
            assertEquals(1, entry.itemStack().getCount());
        }
    }
}
