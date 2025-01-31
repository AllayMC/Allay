package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentEfficiencyType extends EnchantmentType {
    public EnchantmentEfficiencyType() {
        super(new Identifier("minecraft:efficiency"), 15, 5, Rarity.COMMON, ApplicableType.DIGGER_OR_SHEARS);
    }

    @Override
    public int getMinModifiedLevel(int level) {
        return level * 10 - 9;
    }

    @Override
    public int getMaxModifiedLevel(int level) {
        return getMinModifiedLevel(level) + 60;
    }
}
