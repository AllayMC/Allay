package org.allaymc.server.utils;

import org.allaymc.api.item.enchantment.EnchantmentTypes;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.utils.NBTIO;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
class AllayNBTIOTest {
    @Test
    void testFromItemStackNBT() {
        var item1 = ItemTypes.DIAMOND.createItemStack(1, 1);
        item1.addEnchantment(EnchantmentTypes.SHARPNESS, 1);
        item1.setLore(List.of("test"));
        item1.setCustomName("test");
        var nbt = item1.saveNBT();
        var item2 = NBTIO.getAPI().fromItemStackNBT(nbt);
        assertTrue(item1.canMerge(item2));
    }

    // TODO: add tests for other methods
}