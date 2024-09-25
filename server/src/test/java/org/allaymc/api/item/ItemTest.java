package org.allaymc.api.item;

import org.allaymc.api.item.enchantment.type.EnchantmentTypes;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.allaymc.api.item.type.ItemTypes.AIR;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
public class ItemTest {
    @Test
    void testEquals() {
        // ItemStack.equals() should not be overridden.
        ItemStack i1 = AIR.createItemStack();
        ItemStack i2 = AIR.createItemStack();
        assertNotEquals(i1, i2);
    }

    @Test
    void testSerialization() {
        var item1 = ItemTypes.DIAMOND.createItemStack(1, 1);
        item1.addEnchantment(EnchantmentTypes.SHARPNESS, 1);
        item1.setLore(List.of("test"));
        item1.setCustomName("test");
        var nbt = item1.saveNBT();
        var item2 = ItemHelper.fromNBT(nbt);
        assertTrue(item1.canMerge(item2));
    }
}
