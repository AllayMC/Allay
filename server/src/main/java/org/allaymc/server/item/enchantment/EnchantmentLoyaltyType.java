package org.allaymc.server.item.enchantment;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code EnchantmentLoyaltyType} represents the built-in Loyalty enchantment's
 * applicability and table-power rules. Its constructor registers the type and
 * configures {@code ApplicableType.TRIDENT}; the table option generator uses
 * the overridden power bounds when considering each normal enchantment level.
 *
 * @author daoge_cmd
 */
public class EnchantmentLoyaltyType extends EnchantmentType {
    /**
     * Creates and registers the built-in Loyalty enchantment with numeric ID
     * {@code 31}. The type uses {@code Rarity.UNCOMMON}, a normal maximum of
     * {@code 3}, and the {@code ApplicableType.TRIDENT} item category.
     */
    public EnchantmentLoyaltyType() {
        super(new Identifier("minecraft:loyalty"), 31, 3, Rarity.UNCOMMON, ApplicableType.TRIDENT);
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
     * Returns the inclusive lower table-power bound, calculated as
     * {@code level * 7 + 5}.
     */
    @Override
    public int getMinModifiedLevel(int level) {
        return level * 7 + 5;
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
