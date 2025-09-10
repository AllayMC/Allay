package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentQuickChargeType extends EnchantmentType {
    public EnchantmentQuickChargeType() {
        super(new Identifier("minecraft:quick_charge"), 35, 3, Rarity.UNCOMMON, ApplicableType.CROSSBOW);
    }

    @Override
    public int getMinModifiedLevel(int level) {
        return level * 20 - 8;
    }

    @Override
    public int getMaxModifiedLevel(int level) {
        return getMinModifiedLevel(level) + 50;
    }
}
