package org.allaymc.server.item.enchantment;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code EnchantmentSilkTouchType} represents the built-in Silk Touch
 * enchantment's applicability and table-power rules. Its constructor registers
 * the type and configures {@code ApplicableType.DIGGER_OR_SHEARS}; the table
 * option generator uses the overridden power bounds when considering each
 * normal enchantment level.
 *
 * @author daoge_cmd
 */
public class EnchantmentSilkTouchType extends EnchantmentType {
    /**
     * Creates and registers the built-in Silk Touch enchantment with numeric ID
     * {@code 16}. The type uses {@code Rarity.VERY_RARE}, a normal maximum of
     * {@code 1}, and the {@code ApplicableType.DIGGER_OR_SHEARS} item category.
     */
    public EnchantmentSilkTouchType() {
        super(new Identifier("minecraft:silk_touch"), 16, 1, Rarity.VERY_RARE, ApplicableType.DIGGER_OR_SHEARS);
    }

    /**
     * Returns whether {@code other} is an instance of one of this enchantment's
     * conflicting implementation types, including subclasses. The rejected
     * types are {@link EnchantmentFortuneType}; all other values, including
     * {@code null}, return {@code false}.
     */
    @Override
    public boolean isIncompatibleWith(EnchantmentType other) {
        return other instanceof EnchantmentFortuneType;
    }

    /**
     * Returns the inclusive lower table-power bound of {@code 15}. The supplied
     * {@code level} does not affect this bound, so every integer level receives
     * the same threshold during candidate eligibility checks.
     */
    @Override
    public int getMinModifiedLevel(int level) {
        return 15;
    }

    /**
     * Returns the inclusive upper table-power bound of {@code 61}. The supplied
     * {@code level} does not affect this bound, so every integer level receives
     * the same threshold during candidate eligibility checks.
     */
    @Override
    public int getMaxModifiedLevel(int level) {
        return 61;
    }
}
