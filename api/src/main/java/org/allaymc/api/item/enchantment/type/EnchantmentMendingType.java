package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentMendingType extends EnchantmentType {
    public EnchantmentMendingType() {
        super(new Identifier("minecraft:mending"), 26, 3, Rarity.RARE, ApplicableType.DAMAGEABLE);
    }

    @Override
    public boolean isIncompatibleWith(EnchantmentType other) {
        return other instanceof EnchantmentInfinityType;
    }

    @Override
    public boolean isAvailableInEnchantTable() {
        return false;
    }

    @Override
    public int getMinModifiedLevel(int level) {
        return 25;
    }

    @Override
    public int getMaxModifiedLevel(int level) {
        return 75;
    }
}
