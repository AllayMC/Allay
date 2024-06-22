package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentInfinityType extends AbstractEnchantmentType {
    public static final EnchantmentInfinityType INFINITY_TYPE = new EnchantmentInfinityType();

    private EnchantmentInfinityType() {
        super(new Identifier("minecraft:infinity"), 22, 1, Rarity.VERY_RARE);
    }

    @Override
    public boolean checkIncompatible(EnchantmentType other) {
        return other instanceof EnchantmentMendingType;
    }
}
