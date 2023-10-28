package cn.allay.server.item.enchantment;

import cn.allay.api.item.enchantment.EnchantmentHelper;
import cn.allay.testutils.AllayTestExtension;
import org.cloudburstmc.nbt.NbtMap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
public class EnchantmentTest {
    @Test
    void testFromNBT() {
        var nbt = NbtMap.builder()
                .putShort("id", (short) 10)
                .putShort("lvl", (short) 1)
                .build();

        var enchantmentInstance = EnchantmentHelper.fromNBT(nbt);
        assertEquals(10, enchantmentInstance.getType().getId());
        assertEquals(1, enchantmentInstance.getLevel());
    }
}
