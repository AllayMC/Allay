package org.allaymc.api.item;

import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.allaymc.api.item.type.ItemTypes.AIR;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Allay Project 2023/12/1
 *
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
public class ItemStackEqualsTest {

    /**
     * ItemStack.equals() should not be overridden.
     */
    @Test
    void testEquals() {
        ItemStack i1 = AIR.createItemStack();
        ItemStack i2 = AIR.createItemStack();
        assertNotEquals(i1, i2);
    }
}
