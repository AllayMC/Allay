package org.allaymc.server.item.enchantment;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentPowerType extends EnchantmentType {
    public EnchantmentPowerType() {
        super(new Identifier("minecraft:power"), 19, 5, Rarity.COMMON, ApplicableType.BOW);
    }

    @Override
    public int getMinModifiedLevel(int level) {
        return level * 10 - 9;
    }

    @Override
    public int getMaxModifiedLevel(int level) {
        return getMinModifiedLevel(level) + 15;
    }
}
