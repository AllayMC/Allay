package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentFortuneType extends EnchantmentType {
    public EnchantmentFortuneType() {
        super(new Identifier("minecraft:fortune"), 18, 3, Rarity.RARE);
    }

    @Override
    public boolean isIncompatibleWith(EnchantmentType other) {
        return other instanceof EnchantmentSilkTouchType;
    }

    @Override
    public int getMinModifiedLevel(int level) {
        return level * 9 + 6;
    }

    @Override
    public int getMaxModifiedLevel(int level) {
        return level * 10 + 51;
    }
}
