package org.allaymc.server.item.enchantment;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code EnchantmentEfficiencyType} represents the built-in Efficiency
 * enchantment's applicability and table-power rules. Its constructor registers
 * the type and configures {@code ApplicableType.DIGGER_OR_SHEARS}; the table
 * option generator uses the overridden power bounds when considering each
 * normal enchantment level.
 *
 * @author daoge_cmd
 */
public class EnchantmentEfficiencyType extends EnchantmentType {
    /**
     * Creates and registers the built-in Efficiency enchantment with numeric ID
     * {@code 15}. The type uses {@code Rarity.COMMON}, a normal maximum of
     * {@code 5}, and the {@code ApplicableType.DIGGER_OR_SHEARS} item category.
     */
    public EnchantmentEfficiencyType() {
        super(new Identifier("minecraft:efficiency"), 15, 5, Rarity.COMMON, ApplicableType.DIGGER_OR_SHEARS);
    }

    /**
     * Returns the inclusive lower table-power bound, calculated as
     * {@code level * 10 - 9}.
     */
    @Override
    public int getMinModifiedLevel(int level) {
        return level * 10 - 9;
    }

    /**
     * Returns the inclusive upper table-power bound, calculated as
     * {@code getMinModifiedLevel(level) + 60}.
     */
    @Override
    public int getMaxModifiedLevel(int level) {
        return getMinModifiedLevel(level) + 60;
    }
}
