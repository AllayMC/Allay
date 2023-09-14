package cn.allay.server.item.type;

import cn.allay.api.item.init.ItemStackInitInfo;
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
        var itemStack = ItemDiamondStack.DIAMOND_TYPE.createItemStack(ItemStackInitInfo.of(1, 0));
        assertEquals(1, itemStack.getCount());
        assertEquals(0, itemStack.getDamage());
        assertNull(itemStack.getNbt());
        assertEquals(ItemDiamondStack.DIAMOND_TYPE, itemStack.getItemType());

        itemStack.setCount(2);
        itemStack.setDamage(1);
        itemStack.setNbt(
                NbtMap.builder()
                        .putString("test", "114514")
                        .build()
        );

        assertEquals(2, itemStack.getCount());
        assertEquals(1, itemStack.getDamage());
        assertEquals("114514", itemStack.getNbt().getString("test"));

        assertThrows(IllegalArgumentException.class, () -> itemStack.setCount(-1));
    }
}
