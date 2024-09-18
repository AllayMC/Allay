package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentBreachType extends AbstractEnchantmentType {
    public EnchantmentBreachType() {
        super(new Identifier("minecraft:breach"), 40, 4, Rarity.RARE);
    }

    @Override
    public boolean checkIncompatible(EnchantmentType other) {
        return other instanceof EnchantmentDensityType ||
               other instanceof EnchantmentBaneOfArthropodsType ||
               other instanceof EnchantmentSmiteType;
    }
}
