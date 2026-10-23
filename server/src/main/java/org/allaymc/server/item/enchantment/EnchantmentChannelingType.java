package org.allaymc.server.item.enchantment;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code EnchantmentChannelingType} represents the built-in Channeling
 * enchantment's applicability and table-power rules. Its constructor registers
 * the type and configures {@code ApplicableType.TRIDENT}; the table option
 * generator uses the overridden power bounds when considering each normal
 * enchantment level.
 *
 * @author daoge_cmd
 */
public class EnchantmentChannelingType extends EnchantmentType {
    /**
     * Creates and registers the built-in Channeling enchantment with numeric ID
     * {@code 32}. The type uses {@code Rarity.VERY_RARE}, a normal maximum of
     * {@code 1}, and the {@code ApplicableType.TRIDENT} item category.
     */
    public EnchantmentChannelingType() {
        super(new Identifier("minecraft:channeling"), 32, 1, Rarity.VERY_RARE, ApplicableType.TRIDENT);
    }

    /**
     * Returns whether {@code other} is an instance of one of this enchantment's
     * conflicting implementation types, including subclasses. The rejected
     * types are {@link EnchantmentRiptideType}; all other values, including
     * {@code null}, return {@code false}.
     */
    @Override
    public boolean isIncompatibleWith(EnchantmentType other) {
        return other instanceof EnchantmentRiptideType;
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
