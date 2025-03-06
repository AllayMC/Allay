package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentVanishingType extends EnchantmentType {
    public EnchantmentVanishingType() {
        super(new Identifier("minecraft:vanishing"), 28, 1, Rarity.VERY_RARE, ApplicableType.ANY);
    }

    @Override
    public boolean isAvailableInEnchantTable() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return true;
    }

    @Override
    public int getMinModifiedLevel(int level) {
        return 25;
    }

    @Override
    public int getMaxModifiedLevel(int level) {
        return 50;
    }
}
