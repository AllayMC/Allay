package org.allaymc.server.item;

import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemBaseComponent;
import org.allaymc.server.item.component.ItemBaseComponentImpl;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.allaymc.api.item.type.ItemTypes.AIR;
import static org.allaymc.api.item.type.ItemTypes.DIAMOND;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@code ItemStackUniqueIdTest} verifies automatic, explicit, and absent stack network-ID initialization.
 *
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
public class ItemStackUniqueIdTest {
    /**
     * Creates an air stack with no initialization object supplied.
     * Its network ID must remain {@code EMPTY_UNIQUE_ID}, protecting the air factory's
     * predefined initialization and allocation-free stack identity.
     */
    @Test
    void testItemAirStackUniqueId() {
        assertEquals(ItemBaseComponent.EMPTY_UNIQUE_ID, AIR.createItemStack(null).getUniqueId());
    }

    /**
     * Creates one automatically numbered stack and one with an explicit network ID.
     * The automatic stack must use the next counter value, while the explicit
     * selection must retain its supplied ID.
     */
    @Test
    void testItemStackUniqueIdAssignment() {
        var currentId = ItemBaseComponentImpl.getCurrentUniqueIdCounter();
        var itemStack = DIAMOND.createItemStack(ItemStackInitInfo.builder().build());
        assertEquals(currentId, itemStack.getUniqueId());
        itemStack = DIAMOND.createItemStack(ItemStackInitInfo.builder().uniqueId(1).build());
        assertEquals(1, itemStack.getUniqueId());
    }
}
