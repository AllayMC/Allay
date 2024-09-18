package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentRiptideType extends AbstractEnchantmentType {
    public EnchantmentRiptideType() {
        super(new Identifier("minecraft:riptide"), 30, 3, Rarity.RARE);
    }

    @Override
    public boolean checkIncompatible(EnchantmentType other) {
        return other instanceof EnchantmentChannelingType || other instanceof EnchantmentLoyaltyType;
    }
}
