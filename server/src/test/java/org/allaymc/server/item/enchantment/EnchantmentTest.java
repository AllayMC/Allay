package org.allaymc.server.item.enchantment;

import org.allaymc.api.item.enchantment.EnchantmentHelper;
import org.allaymc.testutils.AllayTestExtension;
import org.cloudburstmc.nbt.NbtMap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@code EnchantmentTest} checks restoration of registered enchantment
 * instances from item NBT data. The test supplies numeric type and level
 * shorts, then verifies that the helper resolves the expected registry identity
 * and retains the stored enchantment level.
 *
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
public class EnchantmentTest {
    /**
     * Restores an enchantment from numeric type {@code 10} and level {@code 1}.
     * The assertions protect registry lookup and preservation of the stored
     * level in the returned instance.
     */
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
