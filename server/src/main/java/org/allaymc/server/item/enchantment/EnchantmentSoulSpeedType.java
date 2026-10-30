package org.allaymc.server.item.enchantment;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code EnchantmentSoulSpeedType} represents the built-in Soul Speed
 * enchantment's applicability, acquisition, and table-power rules. Its
 * constructor registers the type with {@code ApplicableType.LEGGINGS}; the
 * acquisition override excludes table selection while power-bound queries
 * remain callable for every supplied integer level.
 *
 * @author daoge_cmd
 */
public class EnchantmentSoulSpeedType extends EnchantmentType {
    /**
     * Creates and registers the built-in Soul Speed enchantment with numeric ID
     * {@code 36}. The type uses {@code Rarity.VERY_RARE}, a normal maximum of
     * {@code 3}, and the {@code ApplicableType.LEGGINGS} item category.
     */
    public EnchantmentSoulSpeedType() {
        super(new Identifier("minecraft:soul_speed"), 36, 3, Rarity.VERY_RARE, ApplicableType.LEGGINGS);
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
     * Returns the inclusive lower table-power bound, calculated as
     * {@code level * 10}.
     */
    @Override
    public int getMinModifiedLevel(int level) {
        return level * 10;
    }

    /**
     * Returns the inclusive upper table-power bound, calculated as
     * {@code getMinModifiedLevel(level) + 15}.
     */
    @Override
    public int getMaxModifiedLevel(int level) {
        return getMinModifiedLevel(level) + 15;
    }

    /**
     * Returns {@code false} to exclude this enchantment from fishing treasure
     * selection.
     */
    @Override
    public boolean isFishable() {
        return false;
    }
}
