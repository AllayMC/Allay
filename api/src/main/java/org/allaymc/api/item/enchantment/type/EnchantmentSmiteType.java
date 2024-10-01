package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentSmiteType extends EnchantmentType {
    public EnchantmentSmiteType() {
        super(new Identifier("minecraft:smite"), 10, 5, Rarity.UNCOMMON);
    }

    @Override
    public boolean isIncompatibleWith(EnchantmentType other) {
        return other instanceof EnchantmentBaneOfArthropodsType ||
               other instanceof EnchantmentSharpnessType ||
               other instanceof EnchantmentBreachType ||
               other instanceof EnchantmentDensityType;
    }

    @Override
    public int getMinModifiedLevel(int level) {
        return level * 8 + 3;
    }

    @Override
    public int getMaxModifiedLevel(int level) {
        return getMinModifiedLevel(level) + 20;
    }
}
