package org.allaymc.server.item;

import org.allaymc.api.item.component.ItemBaseComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.server.item.component.ItemBaseComponentImpl;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.allaymc.api.item.type.ItemTypes.AIR;
import static org.allaymc.api.item.type.ItemTypes.DIAMOND;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
public class ItemStackUniqueIdTest {
    @Test
    void testItemAirStackUniqueId() {
        assertEquals(ItemBaseComponent.EMPTY_UNIQUE_ID, AIR.createItemStack(null).getUniqueId());
    }

    @Test
    void testItemStackUniqueIdAssignment() {
        var currentId = ItemBaseComponentImpl.getCurrentUniqueIdCounter();
        var itemStack = DIAMOND.createItemStack(ItemStackInitInfo.builder().build());
        assertEquals(currentId, itemStack.getUniqueId());
        itemStack = DIAMOND.createItemStack(ItemStackInitInfo.builder().uniqueId(1).build());
        assertEquals(1, itemStack.getUniqueId());
    }
}
