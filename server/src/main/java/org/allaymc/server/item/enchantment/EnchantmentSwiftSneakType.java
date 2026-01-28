package org.allaymc.server.item.enchantment;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentSwiftSneakType extends EnchantmentType {
    public EnchantmentSwiftSneakType() {
        super(new Identifier("minecraft:swift_sneak"), 37, 3, Rarity.VERY_RARE, ApplicableType.LEGGINGS);
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
        return getMinModifiedLevel(level) + 15;
    }

    @Override
    public boolean isFishable() {
        return false;
    }
}
