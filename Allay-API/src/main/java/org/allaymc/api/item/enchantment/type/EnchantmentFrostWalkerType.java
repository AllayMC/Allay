package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentFrostWalkerType extends EnchantmentType {
    public EnchantmentFrostWalkerType() {
        super(new Identifier("minecraft:frost_walker"), 25, 3, Rarity.RARE);
    }

    @Override
    public boolean checkIncompatible(EnchantmentType other) {
        return other instanceof EnchantmentDepthStriderType;
    }
}
