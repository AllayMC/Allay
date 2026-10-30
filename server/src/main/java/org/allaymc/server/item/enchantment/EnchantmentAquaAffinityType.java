package org.allaymc.server.item.enchantment;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code EnchantmentAquaAffinityType} represents the built-in Aqua Affinity
 * enchantment's applicability and table-power rules. Its constructor registers
 * the type and configures {@code ApplicableType.HELMET}; the table option
 * generator uses the overridden power bounds when considering each normal
 * enchantment level.
 *
 * @author daoge_cmd
 */
public class EnchantmentAquaAffinityType extends EnchantmentType {
    /**
     * Creates and registers the built-in Aqua Affinity enchantment with numeric
     * ID {@code 8}. The type uses {@code Rarity.RARE}, a normal maximum of
     * {@code 1}, and the {@code ApplicableType.HELMET} item category.
     */
    public EnchantmentAquaAffinityType() {
        super(new Identifier("minecraft:aqua_affinity"), 8, 1, Rarity.RARE, ApplicableType.HELMET);
    }

    /**
     * Returns the inclusive lower table-power bound of {@code 1}. The supplied
     * {@code level} does not affect this bound, so every integer level receives
     * the same threshold during candidate eligibility checks.
     */
    @Override
    public int getMinModifiedLevel(int level) {
        return 1;
    }

    /**
     * Returns the inclusive upper table-power bound of {@code 41}. The supplied
     * {@code level} does not affect this bound, so every integer level receives
     * the same threshold during candidate eligibility checks.
     */
    @Override
    public int getMaxModifiedLevel(int level) {
        return 41;
    }
}