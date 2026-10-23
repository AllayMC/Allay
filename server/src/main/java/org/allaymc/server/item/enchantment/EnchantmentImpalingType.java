package org.allaymc.server.item.enchantment;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code EnchantmentImpalingType} represents the built-in Impaling
 * enchantment's applicability and table-power rules. Its constructor registers
 * the type and configures {@code ApplicableType.TRIDENT}; the table option
 * generator uses the overridden power bounds when considering each normal
 * enchantment level.
 *
 * @author daoge_cmd
 */
public class EnchantmentImpalingType extends EnchantmentType {
    /**
     * Creates and registers the built-in Impaling enchantment with numeric ID
     * {@code 29}. The type uses {@code Rarity.RARE}, a normal maximum of
     * {@code 5}, and the {@code ApplicableType.TRIDENT} item category.
     */
    public EnchantmentImpalingType() {
        super(new Identifier("minecraft:impaling"), 29, 5, Rarity.RARE, ApplicableType.TRIDENT);
    }

    /**
     * Returns the inclusive lower table-power bound, calculated as
     * {@code level * 8 - 7}.
     */
    @Override
    public int getMinModifiedLevel(int level) {
        return level * 8 - 7;
    }

    /**
     * Returns the inclusive upper table-power bound, calculated as
     * {@code getMinModifiedLevel(level) + 20}.
     */
    @Override
    public int getMaxModifiedLevel(int level) {
        return getMinModifiedLevel(level) + 20;
    }
}
