package org.allaymc.server.item.enchantment;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code EnchantmentRespirationType} represents the built-in Respiration
 * enchantment's applicability and table-power rules. Its constructor registers
 * the type and configures {@code ApplicableType.HELMET}; the table option
 * generator uses the overridden power bounds when considering each normal
 * enchantment level.
 *
 * @author daoge_cmd
 */
public class EnchantmentRespirationType extends EnchantmentType {
    /**
     * Creates and registers the built-in Respiration enchantment with numeric
     * ID {@code 6}. The type uses {@code Rarity.RARE}, a normal maximum of
     * {@code 3}, and the {@code ApplicableType.HELMET} item category.
     */
    public EnchantmentRespirationType() {
        super(new Identifier("minecraft:respiration"), 6, 3, Rarity.RARE, ApplicableType.HELMET);
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
     * {@code level * 300}.
     */
    @Override
    public int getMaxModifiedLevel(int level) {
        return level * 300;
    }
}
