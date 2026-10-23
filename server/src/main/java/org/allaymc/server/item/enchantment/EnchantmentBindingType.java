package org.allaymc.server.item.enchantment;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code EnchantmentBindingType} represents the built-in Binding enchantment's
 * applicability, acquisition, and table-power rules. Its constructor registers
 * the type with {@code ApplicableType.WEARABLE}; the acquisition override
 * excludes table selection while power-bound queries remain callable for every
 * supplied integer level.
 *
 * @author daoge_cmd
 */
public class EnchantmentBindingType extends EnchantmentType {
    /**
     * Creates and registers the built-in Binding enchantment with numeric ID
     * {@code 27}. The type uses {@code Rarity.VERY_RARE}, a normal maximum of
     * {@code 1}, and the {@code ApplicableType.WEARABLE} item category.
     */
    public EnchantmentBindingType() {
        super(new Identifier("minecraft:binding"), 27, 1, Rarity.VERY_RARE, ApplicableType.WEARABLE);
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
     * Returns {@code true} to identify this enchantment as a curse.
     */
    @Override
    public boolean isCursed() {
        return true;
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
     * Returns the inclusive upper table-power bound of {@code 50}. The supplied
     * {@code level} does not affect this bound, so every integer level receives
     * the same threshold during candidate eligibility checks.
     */
    @Override
    public int getMaxModifiedLevel(int level) {
        return 50;
    }
}
