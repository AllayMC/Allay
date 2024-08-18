package org.allaymc.server.item.type;

import org.allaymc.api.item.init.SimpleItemStackInitInfo;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.testutils.AllayTestExtension;
import org.cloudburstmc.nbt.NbtMap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.allaymc.api.item.type.ItemTypes.DIAMOND;
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
        var itemStack = DIAMOND.createItemStack(SimpleItemStackInitInfo.builder().count(1).build());
        assertEquals(1, itemStack.getCount());
        assertEquals(0, itemStack.getMeta());
        assertEquals(0, itemStack.getDurability());
        assertEquals("", itemStack.getCustomName());
        assertEquals(NbtMap.EMPTY, itemStack.getCustomNBTContent());
        assertEquals(List.of(), itemStack.getLore());
        assertEquals(DIAMOND, itemStack.getItemType());
    }

    @Test
    void testGenericFunctions() {
        var diamond = DIAMOND.createItemStack(SimpleItemStackInitInfo.builder().count(1).build());

        // Count
        diamond.setCount(2);
        assertEquals(2, diamond.getCount());
        assertThrows(IllegalArgumentException.class, () -> diamond.setCount(-1));

        // Meta
        diamond.setMeta(1);
        assertEquals(1, diamond.getMeta());
        assertThrows(IllegalArgumentException.class, () -> diamond.setMeta(-1));

        // Lore
        diamond.setLore(List.of("testLore1", "testLore2"));
        assertEquals(List.of("testLore1", "testLore2"), diamond.getLore());

        // CustomName
        diamond.setCustomName("TestCustomName");
        assertEquals("TestCustomName", diamond.getCustomName());

        // Custom NBT Content
        diamond.setCustomNBTContent(
                NbtMap.builder()
                        .putString("testKey", "testValue")
                        .build()
        );
        var savedItemStackNBT = diamond.saveNBT();
        var customNBT = savedItemStackNBT.getCompound("tag").getCompound("CustomNBT");
        assertTrue(customNBT.containsKey("testKey"));
        assertEquals("testValue", customNBT.getString("testKey"));
    }

    @Test
    void testDurability() {
        var diamond = DIAMOND.createItemStack(SimpleItemStackInitInfo.builder().count(1).build());
        var axe = ItemTypes.DIAMOND_AXE.createItemStack(1);

        // Diamond doesn't support durability
        diamond.setDurability(1);
        assertEquals(0, diamond.getDurability());

        // Instead, diamond axe supports durability
        axe.setDurability(1);
        assertEquals(1, axe.getDurability());
        assertThrows(IllegalArgumentException.class, () -> axe.setDurability(-1));
    }
}
