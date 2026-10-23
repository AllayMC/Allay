package org.allaymc.server.item.enchantment;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code EnchantmentFrostWalkerType} represents the built-in Frost Walker
 * enchantment's applicability, acquisition, and table-power rules. Its
 * constructor registers the type with {@code ApplicableType.LEGGINGS}; the
 * acquisition override excludes table selection while power-bound queries
 * remain callable for every supplied integer level.
 *
 * @author daoge_cmd
 */
public class EnchantmentFrostWalkerType extends EnchantmentType {
    /**
     * Creates and registers the built-in Frost Walker enchantment with numeric
     * ID {@code 25}. The type uses {@code Rarity.RARE}, a normal maximum of
     * {@code 3}, and the {@code ApplicableType.LEGGINGS} item category.
     */
    public EnchantmentFrostWalkerType() {
        super(new Identifier("minecraft:frost_walker"), 25, 3, Rarity.RARE, ApplicableType.LEGGINGS);
    }

    /**
     * Returns whether {@code other} is an instance of one of this enchantment's
     * conflicting implementation types, including subclasses. The rejected
     * types are {@link EnchantmentDepthStriderType}; all other values,
     * including {@code null}, return {@code false}.
     */
    @Override
    public boolean isIncompatibleWith(EnchantmentType other) {
        return other instanceof EnchantmentDepthStriderType;
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
     * {@code this.getMinModifiedLevel(level) + 15}.
     */
    @Override
    public int getMaxModifiedLevel(int level) {
        return this.getMinModifiedLevel(level) + 15;
    }
}
