package cn.allay.server.item.type;

import cn.allay.api.item.init.SimpleItemStackInitInfo;
import cn.allay.api.item.interfaces.ItemDiamondStack;
import cn.allay.testutils.AllayTestExtension;
import org.cloudburstmc.nbt.NbtMap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Allay Project 2023/5/20
 *
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
public class AllayItemTypeTest {

    @Test
    void testCreation() {
        var itemStack = ItemDiamondStack.DIAMOND_TYPE.createItemStack(SimpleItemStackInitInfo.builder().count(1).build());
        assertEquals(1, itemStack.getCount());
        assertEquals(0, itemStack.getMeta());
        assertEquals(0, itemStack.getDurability());
        assertEquals(ItemDiamondStack.DIAMOND_TYPE, itemStack.getItemType());

        itemStack.setCount(2);
        itemStack.setMeta(1);
        itemStack.setDurability(1);

        assertEquals(2, itemStack.getCount());
        assertEquals(1, itemStack.getMeta());
        assertEquals(1, itemStack.getDurability());

        assertThrows(IllegalArgumentException.class, () -> itemStack.setCount(-1));
    }
}
