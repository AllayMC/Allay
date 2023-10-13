package cn.allay.server.item;

import cn.allay.api.item.component.base.ItemBaseComponent;
import cn.allay.api.item.component.base.ItemBaseComponentImpl;
import cn.allay.api.item.init.SimpleItemStackInitInfo;
import cn.allay.api.item.interfaces.ItemAirStack;
import cn.allay.api.item.interfaces.ItemDiamondStack;
import cn.allay.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Allay Project 2023/10/14
 *
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
public class ItemStackNetworkIdTest {
    @Test
    void testItemAirStackNetworkId() {
        assertThrows(
                RuntimeException.class,
                () -> ItemAirStack.AIR_TYPE.createItemStack(SimpleItemStackInitInfo.builder().stackNetworkId(1).build())
        );
        assertEquals(ItemBaseComponent.EMPTY_STACK_NETWORK_ID, ItemAirStack.AIR_TYPE.createItemStack(SimpleItemStackInitInfo.builder().build()).getStackNetworkId());
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
