package org.allaymc.server.item.enchantment;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code EnchantmentMendingType} represents the built-in Mending enchantment's
 * applicability, acquisition, and table-power rules. Its constructor registers
 * the type with {@code ApplicableType.DAMAGEABLE}; the acquisition override
 * excludes table selection while power-bound queries remain callable for every
 * supplied integer level.
 *
 * @author daoge_cmd
 */
public class EnchantmentMendingType extends EnchantmentType {
    /**
     * Creates and registers the built-in Mending enchantment with numeric ID
     * {@code 26}. The type uses {@code Rarity.RARE}, a normal maximum of
     * {@code 3}, and the {@code ApplicableType.DAMAGEABLE} item category.
     */
    public EnchantmentMendingType() {
        super(new Identifier("minecraft:mending"), 26, 3, Rarity.RARE, ApplicableType.DAMAGEABLE);
    }

    /**
     * Returns whether {@code other} is an instance of one of this enchantment's
     * conflicting implementation types, including subclasses. The rejected
     * types are {@link EnchantmentInfinityType}; all other values, including
     * {@code null}, return {@code false}.
     */
    @Override
    public boolean isIncompatibleWith(EnchantmentType other) {
        return other instanceof EnchantmentInfinityType;
    }

    /**
     * Returns {@code false} to exclude this enchantment from enchanting-table
     * option selection.
     */
    @Override
    public boolean isAvailableInEnchantTable() {
        return false;
    }

    /**
     * Returns the inclusive lower table-power bound of {@code 25}. The supplied
     * {@code level} does not affect this bound, so every integer level receives
     * the same threshold during candidate eligibility checks.
     */
    @Override
    public int getMinModifiedLevel(int level) {
        return 25;
    }

    /**
     * Returns the inclusive upper table-power bound of {@code 75}. The supplied
     * {@code level} does not affect this bound, so every integer level receives
     * the same threshold during candidate eligibility checks.
     */
    @Override
    public int getMaxModifiedLevel(int level) {
        return 75;
    }
}
