package org.allaymc.server.item.enchantment;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code EnchantmentWindBurstType} represents the built-in Wind Burst
 * enchantment's applicability, acquisition, and table-power rules. Its
 * constructor registers the type with {@code ApplicableType.MACE}; the
 * acquisition override excludes table selection while power-bound queries
 * remain callable for every supplied integer level.
 *
 * @author daoge_cmd
 */
public class EnchantmentWindBurstType extends EnchantmentType {
    /**
     * Creates and registers the built-in Wind Burst enchantment with numeric ID
     * {@code 38}. The type uses {@code Rarity.RARE}, a normal maximum of
     * {@code 3}, and the {@code ApplicableType.MACE} item category.
     */
    public EnchantmentWindBurstType() {
        super(new Identifier("minecraft:wind_burst"), 38, 3, Rarity.RARE, ApplicableType.MACE);
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

    /**
     * Returns {@code false} to exclude this enchantment from fishing treasure
     * selection.
     */
    @Override
    public boolean isFishable() {
        return false;
    }
}
