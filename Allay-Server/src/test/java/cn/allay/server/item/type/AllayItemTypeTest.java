package cn.allay.server.item.type;

import cn.allay.api.item.init.SimpleItemStackInitInfo;
import cn.allay.api.item.interfaces.ItemDiamondStack;
import cn.allay.testutils.AllayTestExtension;
import org.cloudburstmc.nbt.NbtMap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

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
        assertEquals("", itemStack.getCustomName());
        assertEquals(NbtMap.EMPTY, itemStack.getCustomNBTContent());
        assertEquals(List.of(), itemStack.getLore());
        assertEquals(ItemDiamondStack.DIAMOND_TYPE, itemStack.getItemType());
    }

    @Test
    void testGenericFunctions() {
        var itemStack = ItemDiamondStack.DIAMOND_TYPE.createItemStack(SimpleItemStackInitInfo.builder().count(1).build());

        // Count
        itemStack.setCount(2);
        assertEquals(2, itemStack.getCount());
        assertThrows(IllegalArgumentException.class, () -> itemStack.setCount(-1));

        // Meta
        itemStack.setMeta(1);
        assertEquals(1, itemStack.getMeta());
        assertThrows(IllegalArgumentException.class, () -> itemStack.setMeta(-1));

        // Lore
        itemStack.setLore(List.of("testLore1", "testLore2"));
        assertEquals(List.of("testLore1", "testLore2"), itemStack.getLore());

        // Durability
        itemStack.setDurability(1);
        assertEquals(1, itemStack.getDurability());
        assertThrows(IllegalArgumentException.class, () -> itemStack.setDurability(-1));

        // CustomName
        itemStack.setCustomName("TestCustomName");
        assertEquals("TestCustomName", itemStack.getCustomName());

        // Custom NBT Content
        itemStack.setCustomNBTContent(
                NbtMap.builder()
                        .putString("testKey", "testValue")
                        .build()
        );
        var savedItemStackNBT = itemStack.saveNBT();
        assertTrue(savedItemStackNBT.containsKey("testKey"));
        assertEquals("testValue", savedItemStackNBT.getString("testKey"));
    }
}
