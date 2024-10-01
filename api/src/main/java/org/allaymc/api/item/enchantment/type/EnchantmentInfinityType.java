package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentInfinityType extends EnchantmentType {
    public EnchantmentInfinityType() {
        super(new Identifier("minecraft:infinity"), 22, 1, Rarity.VERY_RARE);
    }

    @Override
    public boolean isIncompatibleWith(EnchantmentType other) {
        return other instanceof EnchantmentMendingType;
    }

    @Override
    public int getMinModifiedLevel(int level) {
        return 20;
    }

    @Override
    public int getMaxModifiedLevel(int level) {
        return 50;
    }
}
