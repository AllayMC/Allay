package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentMendingType extends EnchantmentType {
    public EnchantmentMendingType() {
        super(new Identifier("minecraft:mending"), 26, 3, Rarity.RARE);
    }

    @Override
    public boolean checkIncompatible(EnchantmentType other) {
        return other instanceof EnchantmentInfinityType;
    }
}
