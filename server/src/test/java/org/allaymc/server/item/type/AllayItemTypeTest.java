package org.allaymc.server.item.type;

import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.allaymc.api.item.type.ItemTypes.DIAMOND;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
public class AllayItemTypeTest {
    @Test
    void testCreation() {
        var itemStack = DIAMOND.createItemStack(ItemStackInitInfo.builder().count(1).build());
        assertEquals(1, itemStack.getCount());
        assertEquals(0, itemStack.getMeta());
        assertEquals(0, itemStack.getDamage());
        assertEquals("", itemStack.getCustomName());
        assertTrue(itemStack.getPersistentDataContainer().isEmpty());
        assertEquals(List.of(), itemStack.getLore());
        assertEquals(DIAMOND, itemStack.getItemType());
    }

    @Test
    void testGenericFunctions() {
        var diamond = DIAMOND.createItemStack(ItemStackInitInfo.builder().count(1).build());

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
    }

    @Test
    void testDurability() {
        var diamond = DIAMOND.createItemStack(ItemStackInitInfo.builder().count(1).build());
        var axe = ItemTypes.DIAMOND_AXE.createItemStack(1);

        // Diamond doesn't support durability
        diamond.setDamage(1);
        assertEquals(0, diamond.getDamage());

        // Instead, diamond axe supports durability
        axe.setDamage(1);
        assertEquals(1, axe.getDamage());
        assertThrows(IllegalArgumentException.class, () -> axe.setDamage(-1));
    }
}
