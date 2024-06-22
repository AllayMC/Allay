package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * Allay Project 2024/6/18
 *
 * @author daoge_cmd
 */
public class EnchantmentDensityType extends AbstractEnchantmentType {
    public static final EnchantmentDensityType DENSITY_TYPE = new EnchantmentDensityType();

    private EnchantmentDensityType() {
        super(new Identifier("minecraft:density"), 39, 5, Rarity.UNCOMMON);
    }

    @Override
    public boolean checkIncompatible(EnchantmentType other) {
        return other instanceof EnchantmentBreachType ||
               other instanceof EnchantmentBaneOfArthropodsType ||
               other instanceof EnchantmentSmiteType;
    }
}
