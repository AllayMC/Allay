package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentPunchType extends EnchantmentType {
    public EnchantmentPunchType() {
        super(new Identifier("minecraft:punch"), 20, 2, Rarity.RARE, ApplicableType.BOW);
    }

    @Override
    public int getMinModifiedLevel(int level) {
        return level * 20 - 8;
    }

    @Override
    public int getMaxModifiedLevel(int level) {
        return getMinModifiedLevel(level) + 25;
    }
}
