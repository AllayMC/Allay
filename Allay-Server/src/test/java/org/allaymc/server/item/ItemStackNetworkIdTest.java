package org.allaymc.server.item;

import org.allaymc.api.item.component.common.ItemBaseComponent;
import org.allaymc.server.item.component.common.ItemBaseComponentImpl;
import org.allaymc.api.item.init.SimpleItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.item.interfaces.ItemDiamondStack;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

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
        assertEquals(ItemBaseComponent.EMPTY_STACK_NETWORK_ID, ItemAirStack.AIR_TYPE.createItemStack(null).getStackNetworkId());
    }

    @Test
    void testItemStackNetworkIdAssignment() {
        var currentNID = ItemBaseComponentImpl.getCurrentStackNetworkIdCounter();
        var itemStack = ItemDiamondStack.DIAMOND_TYPE.createItemStack(SimpleItemStackInitInfo.builder().build());
        assertEquals(currentNID, itemStack.getStackNetworkId());
        itemStack = ItemDiamondStack.DIAMOND_TYPE.createItemStack(SimpleItemStackInitInfo.builder().stackNetworkId(1).build());
        assertEquals(1, itemStack.getStackNetworkId());
    }
}
