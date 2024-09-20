package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentLoyaltyType extends EnchantmentType {
    public EnchantmentLoyaltyType() {
        super(new Identifier("minecraft:loyalty"), 31, 3, Rarity.UNCOMMON);
    }

    @Override
    public boolean checkIncompatible(EnchantmentType other) {
        return other instanceof EnchantmentRiptideType;
    }
}
