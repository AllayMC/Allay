package org.allaymc.server.item.enchantment;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code EnchantmentPiercingType} represents the built-in Piercing
 * enchantment's applicability and table-power rules. Its constructor registers
 * the type and configures {@code ApplicableType.CROSSBOW}; the table option
 * generator uses the overridden power bounds when considering each normal
 * enchantment level.
 *
 * @author daoge_cmd
 */
public class EnchantmentPiercingType extends EnchantmentType {
    /**
     * Creates and registers the built-in Piercing enchantment with numeric ID
     * {@code 34}. The type uses {@code Rarity.COMMON}, a normal maximum of
     * {@code 4}, and the {@code ApplicableType.CROSSBOW} item category.
     */
    public EnchantmentPiercingType() {
        super(new Identifier("minecraft:piercing"), 34, 4, Rarity.COMMON, ApplicableType.CROSSBOW);
    }

    /**
     * Returns whether {@code other} is an instance of one of this enchantment's
     * conflicting implementation types, including subclasses. The rejected
     * types are {@link EnchantmentMultishotType}; all other values, including
     * {@code null}, return {@code false}.
     */
    @Override
    public boolean isIncompatibleWith(EnchantmentType other) {
        return other instanceof EnchantmentMultishotType;
    }

    /**
     * Returns the inclusive lower table-power bound, calculated as
     * {@code level * 10 - 9}.
     */
    @Override
    public int getMinModifiedLevel(int level) {
        return level * 10 - 9;
    }

    /**
     * Returns the inclusive upper table-power bound, calculated as
     * {@code level * 10 + 41}.
     */
    @Override
    public int getMaxModifiedLevel(int level) {
        return level * 10 + 41;
    }
}
