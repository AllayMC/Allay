package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

public class EnchantmentDensityType extends AbstractEnchantmentType {
    public static final EnchantmentDensityType DENSITY_TYPE = new EnchantmentDensityType();

    //todo max level and Rarity
    private EnchantmentDensityType() {
        super(new Identifier("minecraft:density"), 39, 1, Rarity.VERY_RARE);
    }
}
