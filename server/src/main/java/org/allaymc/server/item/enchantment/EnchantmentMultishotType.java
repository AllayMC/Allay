package org.allaymc.server.item.enchantment;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code EnchantmentMultishotType} represents the built-in Multishot
 * enchantment's applicability and table-power rules. Its constructor registers
 * the type and configures {@code ApplicableType.CROSSBOW}; the table option
 * generator uses the overridden power bounds when considering each normal
 * enchantment level.
 *
 * @author daoge_cmd
 */
public class EnchantmentMultishotType extends EnchantmentType {
    /**
     * Creates and registers the built-in Multishot enchantment with numeric ID
     * {@code 33}. The type uses {@code Rarity.RARE}, a normal maximum of
     * {@code 1}, and the {@code ApplicableType.CROSSBOW} item category.
     */
    public EnchantmentMultishotType() {
        super(new Identifier("minecraft:multishot"), 33, 1, Rarity.RARE, ApplicableType.CROSSBOW);
    }

    /**
     * Returns whether {@code other} is an instance of one of this enchantment's
     * conflicting implementation types, including subclasses. The rejected
     * types are {@link EnchantmentPiercingType}; all other values, including
     * {@code null}, return {@code false}.
     */
    @Override
    public boolean isIncompatibleWith(EnchantmentType other) {
        return other instanceof EnchantmentPiercingType;
    }

    /**
     * Returns the inclusive lower table-power bound of {@code 20}. The supplied
     * {@code level} does not affect this bound, so every integer level receives
     * the same threshold during candidate eligibility checks.
     */
    @Override
    public int getMinModifiedLevel(int level) {
        return 20;
    }

    /**
     * Returns the inclusive upper table-power bound of {@code 70}. The supplied
     * {@code level} does not affect this bound, so every integer level receives
     * the same threshold during candidate eligibility checks.
     */
    @Override
    public int getMaxModifiedLevel(int level) {
        return 70;
    }
}
