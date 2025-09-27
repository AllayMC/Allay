package org.allaymc.api.item;

import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.allaymc.api.item.type.ItemTypes.AIR;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
public class ItemTest {
    @Test
    void testEquals() {
        // ItemStack.equals() should not be overridden.
        ItemStack i1 = AIR.createItemStack();
        ItemStack i2 = AIR.createItemStack();
        assertNotEquals(i1, i2);
    }
}
