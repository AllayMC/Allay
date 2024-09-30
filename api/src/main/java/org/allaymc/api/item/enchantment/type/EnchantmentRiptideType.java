package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentRiptideType extends EnchantmentType {
    public EnchantmentRiptideType() {
        super(new Identifier("minecraft:riptide"), 30, 3, Rarity.RARE);
    }

    @Override
    public boolean isIncompatibleWith(EnchantmentType other) {
        return other instanceof EnchantmentChannelingType || other instanceof EnchantmentLoyaltyType;
    }
}
