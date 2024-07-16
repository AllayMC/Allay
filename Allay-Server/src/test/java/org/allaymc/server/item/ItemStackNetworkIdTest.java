package org.allaymc.server.item;

import org.allaymc.api.item.component.common.ItemBaseComponent;
import org.allaymc.api.item.init.SimpleItemStackInitInfo;
import org.allaymc.server.item.component.common.ItemBaseComponentImpl;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.allaymc.api.item.type.ItemTypes.AIR;
import static org.allaymc.api.item.type.ItemTypes.DIAMOND;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Allay Project 2023/10/14
 *
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
public class ItemStackNetworkIdTest {
    @Test
    void testItemAirStackNetworkId() {
        assertEquals(ItemBaseComponent.EMPTY_STACK_NETWORK_ID, AIR.createItemStack(null).getStackNetworkId());
    }

    @Test
    void testItemStackNetworkIdAssignment() {
        var currentNID = ItemBaseComponentImpl.getCurrentStackNetworkIdCounter();
        var itemStack = DIAMOND.createItemStack(SimpleItemStackInitInfo.builder().build());
        assertEquals(currentNID, itemStack.getStackNetworkId());
        itemStack = DIAMOND.createItemStack(SimpleItemStackInitInfo.builder().stackNetworkId(1).build());
        assertEquals(1, itemStack.getStackNetworkId());
    }
}
