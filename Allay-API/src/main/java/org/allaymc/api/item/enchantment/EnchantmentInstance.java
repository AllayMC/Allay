package org.allaymc.api.item.enchantment;

import org.cloudburstmc.nbt.NbtMap;

/**
 * @author daoge_cmd
 */
public interface EnchantmentInstance {

    EnchantmentType getType();

    int getLevel();

    default EnchantmentInstance setLevel(int level) {
        return getType().createInstance(level);
    }

    default NbtMap saveNBT() {
        return NbtMap.builder()
                .putShort("id", (short) getType().getId())
                .putShort("lvl", (short) getLevel())
                .build();
    }
}
