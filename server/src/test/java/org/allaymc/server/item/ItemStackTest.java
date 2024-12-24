package org.allaymc.server.item;

import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
public class ItemStackTest {
    @Test
    void testSetCountAndMeta() {
        var itemStack = ItemTypes.DIAMOND.createItemStack(1);
        assertDoesNotThrow(() -> {
            itemStack.setCount(0);
            itemStack.setMeta(0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            itemStack.setCount(-1);
            itemStack.setMeta(-1);
        });
    }
}
