package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentChannelingType extends EnchantmentType {
    public EnchantmentChannelingType() {
        super(new Identifier("minecraft:channeling"), 32, 1, Rarity.VERY_RARE);
    }

    @Override
    public boolean checkIncompatible(EnchantmentType other) {
        return other instanceof EnchantmentRiptideType;
    }
}
