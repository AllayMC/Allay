package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentSilkTouchType extends EnchantmentType {
    public EnchantmentSilkTouchType() {
        super(new Identifier("minecraft:silk_touch"), 16, 1, Rarity.VERY_RARE, ApplicableType.DIGGER_OR_SHEARS);
    }

    @Override
    public boolean isIncompatibleWith(EnchantmentType other) {
        return other instanceof EnchantmentFortuneType;
    }

    @Override
    public int getMinModifiedLevel(int level) {
        return 15;
    }

    @Override
    public int getMaxModifiedLevel(int level) {
        return 61;
    }
}
