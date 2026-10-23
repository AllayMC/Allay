package org.allaymc.api.item.enchantment;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.registry.Registries;
import org.cloudburstmc.nbt.NbtMap;

/**
 * {@code EnchantmentHelper} restores enchantment instances from their item NBT
 * representation. The numeric {@code "id"} selects a registered type, and
 * {@code "lvl"} supplies its level; unknown identifiers produce a warning and
 * no instance instead of introducing an unregistered enchantment.
 *
 * @author daoge_cmd
 */
@Slf4j
@UtilityClass
public class EnchantmentHelper {
    /**
     * Restores a cached enchantment instance from {@code "id"} and
     * {@code "lvl"} short values. Missing or non-short tags use {@code 0},
     * following {@code NbtMap.getShort}; registered types accept the resulting
     * level without checking their normal maximum. An unknown numeric
     * identifier is logged and returns {@code null}, which callers must handle
     * before reading the instance.
     */
    public EnchantmentInstance fromNBT(NbtMap nbtMap) {
        var id = nbtMap.getShort("id");
        var enchantmentType = Registries.ENCHANTMENTS.getByK1((int) id);
        // Unknown registry IDs have no type from which to create an instance.
        // The warning identifies the missing ID, and the return value signals failed resolution.
        if (enchantmentType == null) {
            log.warn("Unknown enchantment id {}", id);
            return null;
        }
        return enchantmentType.createInstance(nbtMap.getShort("lvl"));
    }
}
