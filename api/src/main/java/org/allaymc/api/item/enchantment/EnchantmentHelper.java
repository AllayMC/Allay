package org.allaymc.api.item.enchantment;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.registry.Registries;
import org.cloudburstmc.nbt.NbtMap;

/**
 * Helper class for enchantments.
 *
 * @author daoge_cmd
 */
@Slf4j
@UtilityClass
public class EnchantmentHelper {
    /**
     * Create an enchantment instance from NBT.
     *
     * @param nbtMap The NBT map to create the enchantment instance from.
     * @return The enchantment instance.
     */
    public EnchantmentInstance fromNBT(NbtMap nbtMap) {
        var id = nbtMap.getShort("id");
        var enchantmentType = Registries.ENCHANTMENTS.getByK1((int) id);
        if (enchantmentType == null) {
            log.warn("Unknown enchantment id {}", id);
            return null;
        }
        return enchantmentType.createInstance(nbtMap.getShort("lvl"));
    }
}
