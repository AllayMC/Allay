package org.allaymc.api.item;

import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.allaymc.api.item.type.ItemTypes.AIR;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * {@code ItemTest} verifies reference-based equality for independently created item stacks.
 *
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
public class ItemTest {
    /**
     * Creates two air stacks through the registered item factory.
     * The stacks must compare unequal, protecting per-instance identity rather than
     * equality derived from their matching item contents.
     */
    @Test
    void testEquals() {
        ItemStack i1 = AIR.createItemStack();
        ItemStack i2 = AIR.createItemStack();
        assertNotEquals(i1, i2);
    }
}
