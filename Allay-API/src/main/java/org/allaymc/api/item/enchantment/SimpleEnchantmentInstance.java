package org.allaymc.api.item.enchantment;

/**
 * Allay Project 2023/10/21
 *
 * @author daoge_cmd
 */
public class SimpleEnchantmentInstance implements EnchantmentInstance {
    protected final EnchantmentType type;
    protected final short level;

    public SimpleEnchantmentInstance(EnchantmentType type, short level) {
        this.type = type;
        this.level = level;
    }

    @Override
    public EnchantmentType getType() {
        return type;
    }

    @Override
    public short getLevel() {
        return level;
    }
}
