package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentVanishingType extends EnchantmentType {
    public EnchantmentVanishingType() {
        super(new Identifier("minecraft:vanishing"), 28, 1, Rarity.VERY_RARE);
    }

    @Override
    public boolean isAvailableInEnchantTable() {
        return false;
    }
}
