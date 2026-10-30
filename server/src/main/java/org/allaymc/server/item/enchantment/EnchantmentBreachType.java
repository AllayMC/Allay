package org.allaymc.server.item.enchantment;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code EnchantmentBreachType} represents the built-in Breach enchantment's
 * applicability and table-power rules. Its constructor registers the type and
 * configures {@code ApplicableType.MACE}; the table option generator uses the
 * overridden power bounds when considering each normal enchantment level.
 *
 * @author daoge_cmd
 */
public class EnchantmentBreachType extends EnchantmentType {
    /**
     * Creates and registers the built-in Breach enchantment with numeric ID
     * {@code 40}. The type uses {@code Rarity.RARE}, a normal maximum of
     * {@code 4}, and the {@code ApplicableType.MACE} item category.
     */
    public EnchantmentBreachType() {
        super(new Identifier("minecraft:breach"), 40, 4, Rarity.RARE, ApplicableType.MACE);
    }

    /**
     * Returns whether {@code other} is an instance of one of this enchantment's
     * conflicting implementation types, including subclasses. The rejected
     * types are {@link EnchantmentDensityType},
     * {@link EnchantmentBaneOfArthropodsType}, {@link EnchantmentSmiteType};
     * all other values, including {@code null}, return {@code false}.
     */
    @Override
    public boolean isIncompatibleWith(EnchantmentType other) {
        return other instanceof EnchantmentDensityType ||
               other instanceof EnchantmentBaneOfArthropodsType ||
               other instanceof EnchantmentSmiteType;
    }

    /**
     * Returns the inclusive lower table-power bound, calculated as
     * {@code level * 9 + 6}.
     */
    @Override
    public int getMinModifiedLevel(int level) {
        return level * 9 + 6;
    }

    /**
     * Returns the inclusive upper table-power bound, calculated as
     * {@code getMinModifiedLevel(level) + 50}.
     */
    @Override
    public int getMaxModifiedLevel(int level) {
        return getMinModifiedLevel(level) + 50;
    }
}
