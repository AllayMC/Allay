package org.allaymc.api.item;

import org.allaymc.api.item.interfaces.ItemAirStack;
import org.junit.jupiter.api.Test;

/**
 * Allay Project 2023/12/1
 *
 * @author daoge_cmd
 */
public class ItemStackEqualsTest {

    /**
     * ItemStack.equals() should not be overridden.
     */
    @Test
    void testEquals() {
        ItemStack i1 = ItemAirStack.AIR_TYPE.createItemStack();
        ItemStack i2 = ItemAirStack.AIR_TYPE.createItemStack();

        assert !i1.equals(i2);
    }
}
