package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentFrostWalkerType extends EnchantmentType {
    public EnchantmentFrostWalkerType() {
        super(new Identifier("minecraft:frost_walker"), 25, 3, Rarity.RARE);
    }

    @Override
    public boolean isIncompatibleWith(EnchantmentType other) {
        return other instanceof EnchantmentDepthStriderType;
    }

    @Override
    public boolean isAvailableInEnchantTable() {
        return false;
    }

    @Override
    public int getMinModifiedLevel(int level) {
        return level * 10;
    }

    @Override
    public int getMaxModifiedLevel(int level) {
        return this.getMinModifiedLevel(level) + 15;
    }
}
