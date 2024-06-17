package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
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
        // TODO: Check rarity
        super(new Identifier("minecraft:density"), 39, 5, Rarity.VERY_RARE);
    }
}
