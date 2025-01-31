package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentPiercingType extends EnchantmentType {
    public EnchantmentPiercingType() {
        super(new Identifier("minecraft:piercing"), 34, 4, Rarity.COMMON, ApplicableType.CROSSBOW);
    }

    @Override
    public boolean isIncompatibleWith(EnchantmentType other) {
        return other instanceof EnchantmentMultishotType;
    }

    @Override
    public int getMinModifiedLevel(int level) {
        return level * 10 - 9;
    }

    @Override
    public int getMaxModifiedLevel(int level) {
        return level * 10 + 41;
    }
}
