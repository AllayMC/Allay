package org.allaymc.server.item.type;

import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemEdibleComponent;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.allaymc.api.item.type.ItemTypes.DIAMOND;
import static org.junit.jupiter.api.Assertions.*;

/**
 * {@code AllayItemTypeTest} verifies component-backed item defaults, mutation rules, and specialized capabilities.
 *
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
public class AllayItemTypeTest {
    /**
     * Creates a diamond stack with only its count explicitly initialized.
     * The stack must expose the expected type, zero metadata and damage, empty
     * display state, and an empty persistent-data container.
     */
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

    /**
     * Mutates count, metadata, lore, and custom name on a diamond stack.
     * The stored values must be readable through the generated item interface,
     * and negative count or metadata must be rejected.
     */
    @Test
    void testGenericFunctions() {
        var diamond = DIAMOND.createItemStack(ItemStackInitInfo.builder().count(1).build());

        diamond.setCount(2);
        assertEquals(2, diamond.getCount());
        assertThrows(IllegalArgumentException.class, () -> diamond.setCount(-1));

        diamond.setMeta(1);
        assertEquals(1, diamond.getMeta());
        assertThrows(IllegalArgumentException.class, () -> diamond.setMeta(-1));

        diamond.setLore(List.of("testLore1", "testLore2"));
        assertEquals(List.of("testLore1", "testLore2"), diamond.getLore());

        diamond.setCustomName("TestCustomName");
        assertEquals("TestCustomName", diamond.getCustomName());
    }

    /**
     * Attempts durability changes on a diamond item and a diamond axe.
     * The ordinary diamond must ignore damage, while the axe stores positive damage
     * and rejects a negative damage value.
     */
    @Test
    void testDurability() {
        var diamond = DIAMOND.createItemStack(ItemStackInitInfo.builder().count(1).build());
        var axe = ItemTypes.DIAMOND_AXE.createItemStack(1);

        diamond.setDamage(1);
        assertEquals(0, diamond.getDamage());

        axe.setDamage(1);
        assertEquals(1, axe.getDamage());
        assertThrows(IllegalArgumentException.class, () -> axe.setDamage(-1));
    }

    /**
     * Creates a milk bucket through its registered item type.
     * The stack must expose the edible capability and report both drinking behavior
     * and permission to consume regardless of hunger.
     */
    @Test
    void testMilkBucketIsDrinkable() {
        var milkBucket = ItemTypes.MILK_BUCKET.createItemStack(1);

        assertInstanceOf(ItemEdibleComponent.class, milkBucket);
        var edibleComponent = (ItemEdibleComponent) milkBucket;
        assertTrue(edibleComponent.isDrink());
        assertTrue(edibleComponent.canBeAlwaysEaten());
    }
}
