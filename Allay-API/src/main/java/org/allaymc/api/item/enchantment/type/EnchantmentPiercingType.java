package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentPiercingType extends EnchantmentType {
    public EnchantmentPiercingType() {
        super(new Identifier("minecraft:piercing"), 34, 4, Rarity.COMMON);
    }

    @Override
    public boolean checkIncompatible(EnchantmentType other) {
        return other instanceof EnchantmentMultishotType;
    }
}
