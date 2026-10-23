package org.allaymc.server.item.enchantment;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code EnchantmentVanishingType} represents the built-in Vanishing
 * enchantment's applicability, acquisition, and table-power rules. Its
 * constructor registers the type with {@code ApplicableType.ANY}; the
 * acquisition override excludes table selection while power-bound queries
 * remain callable for every supplied integer level.
 *
 * @author daoge_cmd
 */
public class EnchantmentVanishingType extends EnchantmentType {
    /**
     * Creates and registers the built-in Vanishing enchantment with numeric ID
     * {@code 28}. The type uses {@code Rarity.VERY_RARE}, a normal maximum of
     * {@code 1}, and the {@code ApplicableType.ANY} item category.
     */
    public EnchantmentVanishingType() {
        super(new Identifier("minecraft:vanishing"), 28, 1, Rarity.VERY_RARE, ApplicableType.ANY);
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
