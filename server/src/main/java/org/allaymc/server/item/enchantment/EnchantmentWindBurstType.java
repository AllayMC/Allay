package org.allaymc.server.item.enchantment;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentWindBurstType extends EnchantmentType {
    public EnchantmentWindBurstType() {
        super(new Identifier("minecraft:wind_burst"), 38, 3, Rarity.RARE, ApplicableType.MACE);
    }

    @Override
    public boolean isAvailableInEnchantTable() {
        return false;
    }

    @Override
    public int getMinModifiedLevel(int level) {
        return level * 9 + 6;
    }

    @Override
    public int getMaxModifiedLevel(int level) {
        return getMinModifiedLevel(level) + 50;
    }

    @Override
    public boolean isFishable() {
        return false;
    }
}
