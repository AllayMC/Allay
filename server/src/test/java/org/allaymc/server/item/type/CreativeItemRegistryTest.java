package org.allaymc.server.item.type;

import org.allaymc.api.registry.Registries;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * {@code CreativeItemRegistryTest} checks the initialized creative registry's item-count convention.
 *
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
public class CreativeItemRegistryTest {
    /**
     * Reads the creative registry populated by server test initialization.
     * The registry must contain entries, and every registered creative item stack
     * must carry a count of exactly {@code 1}.
     */
    @Test
    void testCreativeItemRegistry() {
        assertFalse(Registries.CREATIVE_ITEMS.getEntries().isEmpty());
        for (var entry : Registries.CREATIVE_ITEMS.getEntries()) {
            assertEquals(1, entry.itemStack().getCount());
        }
    }
}
