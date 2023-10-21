package cn.allay.api.item.enchantment;

import org.cloudburstmc.nbt.NbtMap;

/**
 * Allay Project 2023/10/21
 *
 * @author daoge_cmd
 */
public interface EnchantmentInstance {

    EnchantmentType getType();

    short getLevel();

    default EnchantmentInstance setLevel(short level) {
        return getType().createInstance(level);
    }

    default NbtMap saveNBT() {
        return NbtMap.builder()
                .putShort("id", getType().getId())
                .putShort("lvl", getLevel())
                .build();
    }
}
