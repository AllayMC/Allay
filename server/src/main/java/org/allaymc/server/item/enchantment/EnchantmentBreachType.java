package org.allaymc.server.item.enchantment;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentBreachType extends EnchantmentType {
    public EnchantmentBreachType() {
        super(new Identifier("minecraft:breach"), 40, 4, Rarity.RARE, ApplicableType.MACE);
    }

    @Override
    public boolean isIncompatibleWith(EnchantmentType other) {
        return other instanceof EnchantmentDensityType ||
               other instanceof EnchantmentBaneOfArthropodsType ||
               other instanceof EnchantmentSmiteType;
    }

    @Override
    public int getMinModifiedLevel(int level) {
        return level * 9 + 6;
    }

    @Override
    public int getMaxModifiedLevel(int level) {
        return getMinModifiedLevel(level) + 50;
    }
}
