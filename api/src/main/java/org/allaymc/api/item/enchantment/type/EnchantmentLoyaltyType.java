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
    public boolean isIncompatibleWith(EnchantmentType other) {
        return other instanceof EnchantmentRiptideType;
    }

    @Override
    public int getMinModifiedLevel(int level) {
        return level * 7 + 5;
    }

    @Override
    public int getMaxModifiedLevel(int level) {
        return 50;
    }
}
