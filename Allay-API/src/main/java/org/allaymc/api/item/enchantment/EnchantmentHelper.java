package org.allaymc.api.item.enchantment;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.nbt.NbtMap;

/**
 * Allay Project 2023/10/21
 *
 * @author daoge_cmd
 */
@UtilityClass
@Slf4j
public class EnchantmentHelper {
    public EnchantmentInstance fromNBT(NbtMap nbtMap) {
        var id = nbtMap.getShort("id");
        var enchantmentType = EnchantmentRegistry.getRegistry().getByK1(id);
        if (enchantmentType == null) {
            log.warn("Unknown enchantment id {}", id);
            return null;
        }
        return enchantmentType.createInstance(nbtMap.getShort("lvl"));
    }
}
