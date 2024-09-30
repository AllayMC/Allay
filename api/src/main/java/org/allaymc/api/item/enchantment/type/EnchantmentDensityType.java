package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentDensityType extends EnchantmentType {
    public EnchantmentDensityType() {
        super(new Identifier("minecraft:density"), 39, 5, Rarity.UNCOMMON);
    }

    @Override
    public boolean isIncompatibleWith(EnchantmentType other) {
        return other instanceof EnchantmentBreachType ||
               other instanceof EnchantmentBaneOfArthropodsType ||
               other instanceof EnchantmentSmiteType;
    }
}
