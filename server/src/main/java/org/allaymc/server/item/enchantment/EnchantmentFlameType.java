package org.allaymc.server.item.enchantment;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code EnchantmentFlameType} represents the built-in Flame enchantment's
 * applicability and table-power rules. Its constructor registers the type and
 * configures {@code ApplicableType.BOW}; the table option generator uses the
 * overridden power bounds when considering each normal enchantment level.
 *
 * @author daoge_cmd
 */
public class EnchantmentFlameType extends EnchantmentType {
    /**
     * Creates and registers the built-in Flame enchantment with numeric ID
     * {@code 21}. The type uses {@code Rarity.RARE}, a normal maximum of
     * {@code 1}, and the {@code ApplicableType.BOW} item category.
     */
    public EnchantmentFlameType() {
        super(new Identifier("minecraft:flame"), 21, 1, Rarity.RARE, ApplicableType.BOW);
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
     * Returns the inclusive upper table-power bound of {@code 50}. The supplied
     * {@code level} does not affect this bound, so every integer level receives
     * the same threshold during candidate eligibility checks.
     */
    @Override
    public int getMaxModifiedLevel(int level) {
        return 50;
    }
}
