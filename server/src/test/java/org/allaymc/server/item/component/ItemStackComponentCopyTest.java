package org.allaymc.server.item.component;

import org.allaymc.api.item.component.ItemEdibleComponent;
import org.allaymc.api.item.component.ItemRepairableComponent;
import org.allaymc.api.item.component.ItemToolComponent;
import org.allaymc.api.item.component.ItemWearableComponent;
import org.allaymc.api.item.data.ItemLockMode;
import org.allaymc.api.item.enchantment.EnchantmentTypes;
import org.allaymc.api.item.interfaces.ItemLeatherHelmetStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.pdc.PersistentDataType;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.component.ComponentClass;
import org.allaymc.server.registry.InternalRegistries;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.awt.Color;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * {@code ItemStackComponentCopyTest} verifies stateless singleton component
 * sharing across item stacks and state isolation for both fast-path and
 * NBT-backed {@code ItemStack.copy()} operations.
 *
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
class ItemStackComponentCopyTest {

    /**
     * Creates multiple instances of swords, foods, and armor items through their
     * registered item types. Stateless behavior components must be shared as the
     * exact same instance while mutable per-stack damage remains isolated.
     */
    @Test
    void testStatelessComponentsAreSharedSingletons() {
        var sword1 = ItemTypes.DIAMOND_SWORD.createItemStack(1);
        var sword2 = ItemTypes.DIAMOND_SWORD.createItemStack(1);
        assertSame(
                ((ComponentClass) sword1).getComponent(ItemToolComponent.class),
                ((ComponentClass) sword2).getComponent(ItemToolComponent.class)
        );
        assertSame(
                ((ComponentClass) sword1).getComponent(ItemRepairableComponent.class),
                ((ComponentClass) sword2).getComponent(ItemRepairableComponent.class)
        );
        sword1.setDamage(10);
        assertEquals(10, sword1.getDamage());
        assertEquals(0, sword2.getDamage());

        var apple1 = ItemTypes.APPLE.createItemStack(1);
        var apple2 = ItemTypes.APPLE.createItemStack(1);
        assertSame(
                ((ComponentClass) apple1).getComponent(ItemEdibleComponent.class),
                ((ComponentClass) apple2).getComponent(ItemEdibleComponent.class)
        );

        var helmet1 = ItemTypes.DIAMOND_HELMET.createItemStack(1);
        var helmet2 = ItemTypes.DIAMOND_HELMET.createItemStack(1);
        assertSame(
                ((ComponentClass) helmet1).getComponent(ItemWearableComponent.class),
                ((ComponentClass) helmet2).getComponent(ItemWearableComponent.class)
        );
    }

    /**
     * Copies a heavily customized diamond sword through the in-memory fast path
     * and mutates the copy afterward. All metadata, enchantments, lore, lock
     * mode, and persistent data must transfer while keeping collections isolated.
     */
    @Test
    void testFastPathCopyPreservesAndIsolatesState() {
        var original = ItemTypes.DIAMOND_SWORD.createItemStack(1);
        var pdcKey = new Identifier("test:owner");
        original.setDamage(25);
        original.setRepairCost(3);
        original.setCustomName("Excalibur");
        original.setLore(List.of("Line 1", "Line 2"));
        original.addEnchantment(EnchantmentTypes.SHARPNESS, 5);
        original.setLockMode(ItemLockMode.LOCK_IN_INVENTORY);
        original.getPersistentDataContainer().set(pdcKey, PersistentDataType.STRING, "alice");

        var copiedWithNewId = original.copy(true);
        assertNotEquals(original.getUniqueId(), copiedWithNewId.getUniqueId());
        assertEquals(25, copiedWithNewId.getDamage());
        assertEquals(3, copiedWithNewId.getRepairCost());
        assertEquals("Excalibur", copiedWithNewId.getCustomName());
        assertEquals(List.of("Line 1", "Line 2"), copiedWithNewId.getLore());
        assertEquals(5, copiedWithNewId.getEnchantmentLevel(EnchantmentTypes.SHARPNESS));
        assertEquals(ItemLockMode.LOCK_IN_INVENTORY, copiedWithNewId.getLockMode());
        assertEquals("alice", copiedWithNewId.getPersistentDataContainer().get(pdcKey, PersistentDataType.STRING));

        var copiedWithSameId = original.copy(false);
        assertEquals(original.getUniqueId(), copiedWithSameId.getUniqueId());

        // Mutating the copy must not affect the original stack's collections or PDC.
        copiedWithNewId.setDamage(50);
        copiedWithNewId.setLore(List.of("Changed"));
        copiedWithNewId.removeEnchantment(EnchantmentTypes.SHARPNESS);
        copiedWithNewId.getPersistentDataContainer().set(pdcKey, PersistentDataType.STRING, "bob");

        assertEquals(25, original.getDamage());
        assertEquals(List.of("Line 1", "Line 2"), original.getLore());
        assertEquals(5, original.getEnchantmentLevel(EnchantmentTypes.SHARPNESS));
        assertEquals("alice", original.getPersistentDataContainer().get(pdcKey, PersistentDataType.STRING));
    }

    /**
     * Copies a dyed and trimmed leather helmet whose stateful sibling components
     * listen to extra-tag events. The fallback NBT copy path must preserve both
     * custom color and armor trim independently from the original stack.
     */
    @Test
    void testStatefulSiblingCopyPreservesColorAndTrim() {
        var pattern = InternalRegistries.TRIM_PATTERNS.getContent().values().iterator().next();
        var material = InternalRegistries.TRIM_MATERIALS.getContent().values().iterator().next();
        var original = ItemTypes.LEATHER_HELMET.createItemStack(1);
        original.setCustomColor(new Color(0x12, 0x34, 0x56));
        original.trim(pattern, material);

        var copied = (ItemLeatherHelmetStack) original.copy(true);
        assertEquals(new Color(0x12, 0x34, 0x56), copied.getCustomColor());
        assertEquals(pattern, copied.getPattern());
        assertEquals(material, copied.getMaterial());

        copied.setCustomColor(new Color(0xAA, 0xBB, 0xCC));
        assertEquals(new Color(0x12, 0x34, 0x56), original.getCustomColor());
    }
}
