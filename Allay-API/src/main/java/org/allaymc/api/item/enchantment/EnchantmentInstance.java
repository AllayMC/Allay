package org.allaymc.api.item.enchantment;

import lombok.AllArgsConstructor;
import org.cloudburstmc.nbt.NbtMap;

/**
 * @author daoge_cmd
 */
@AllArgsConstructor
public class EnchantmentInstance {
    protected final EnchantmentType type;
    protected final int level;

    public EnchantmentType getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public EnchantmentInstance setLevel(int level) {
        return getType().createInstance(level);
    }

    public NbtMap saveNBT() {
        return NbtMap.builder()
                .putShort("id", (short) getType().getId())
                .putShort("lvl", (short) getLevel())
                .build();
    }
}
