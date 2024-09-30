package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentSoulSpeedType extends EnchantmentType {
    public EnchantmentSoulSpeedType() {
        super(new Identifier("minecraft:soul_speed"), 36, 3, Rarity.VERY_RARE);
    }

    @Override
    public boolean isAvailableInEnchantTable() {
        return false;
    }
}
