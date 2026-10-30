package org.allaymc.server.item.enchantment;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code EnchantmentDensityType} represents the built-in Density enchantment's
 * applicability and table-power rules. Its constructor registers the type and
 * configures {@code ApplicableType.MACE}; the table option generator uses the
 * overridden power bounds when considering each normal enchantment level.
 *
 * @author daoge_cmd
 */
public class EnchantmentDensityType extends EnchantmentType {
    /**
     * Creates and registers the built-in Density enchantment with numeric ID
     * {@code 39}. The type uses {@code Rarity.UNCOMMON}, a normal maximum of
     * {@code 5}, and the {@code ApplicableType.MACE} item category.
     */
    public EnchantmentDensityType() {
        super(new Identifier("minecraft:density"), 39, 5, Rarity.UNCOMMON, ApplicableType.MACE);
    }

    /**
     * Returns whether {@code other} is an instance of one of this enchantment's
     * conflicting implementation types, including subclasses. The rejected
     * types are {@link EnchantmentBreachType},
     * {@link EnchantmentBaneOfArthropodsType}, {@link EnchantmentSmiteType};
     * all other values, including {@code null}, return {@code false}.
     */
    @Override
    public boolean isIncompatibleWith(EnchantmentType other) {
        return other instanceof EnchantmentBreachType ||
               other instanceof EnchantmentBaneOfArthropodsType ||
               other instanceof EnchantmentSmiteType;
    }

    /**
     * Returns the inclusive lower table-power bound, calculated as
     * {@code level * 8 - 3}.
     */
    @Override
    public int getMinModifiedLevel(int level) {
        return level * 8 - 3;
    }

    /**
     * Returns the inclusive upper table-power bound, calculated as
     * {@code getMinModifiedLevel(level) + 20}.
     */
    @Override
    public int getMaxModifiedLevel(int level) {
        return getMinModifiedLevel(level) + 20;
    }
}
