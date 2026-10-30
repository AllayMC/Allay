package org.allaymc.server.item.enchantment;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code EnchantmentPowerType} represents the built-in Power enchantment's
 * applicability and table-power rules. Its constructor registers the type and
 * configures {@code ApplicableType.BOW}; the table option generator uses the
 * overridden power bounds when considering each normal enchantment level.
 *
 * @author daoge_cmd
 */
public class EnchantmentPowerType extends EnchantmentType {
    /**
     * Creates and registers the built-in Power enchantment with numeric ID
     * {@code 19}. The type uses {@code Rarity.COMMON}, a normal maximum of
     * {@code 5}, and the {@code ApplicableType.BOW} item category.
     */
    public EnchantmentPowerType() {
        super(new Identifier("minecraft:power"), 19, 5, Rarity.COMMON, ApplicableType.BOW);
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
     * {@code getMinModifiedLevel(level) + 15}.
     */
    @Override
    public int getMaxModifiedLevel(int level) {
        return getMinModifiedLevel(level) + 15;
    }
}
