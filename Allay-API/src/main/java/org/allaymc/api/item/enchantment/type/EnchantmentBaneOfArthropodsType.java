package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentBaneOfArthropodsType extends AbstractEnchantmentType {
    public EnchantmentBaneOfArthropodsType() {
        super(new Identifier("minecraft:bane_of_arthropods"), 11, 5, Rarity.UNCOMMON);
    }

    @Override
    public boolean checkIncompatible(EnchantmentType other) {
        return other instanceof EnchantmentSmiteType ||
               other instanceof EnchantmentSharpnessType ||
               other instanceof EnchantmentBreachType ||
               other instanceof EnchantmentDensityType;
    }
}
