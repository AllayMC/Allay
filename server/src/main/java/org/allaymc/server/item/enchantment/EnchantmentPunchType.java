package org.allaymc.server.item.enchantment;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code EnchantmentPunchType} represents the built-in Punch enchantment's
 * applicability and table-power rules. Its constructor registers the type and
 * configures {@code ApplicableType.BOW}; the table option generator uses the
 * overridden power bounds when considering each normal enchantment level.
 *
 * @author daoge_cmd
 */
public class EnchantmentPunchType extends EnchantmentType {
    /**
     * Creates and registers the built-in Punch enchantment with numeric ID
     * {@code 20}. The type uses {@code Rarity.RARE}, a normal maximum of
     * {@code 2}, and the {@code ApplicableType.BOW} item category.
     */
    public EnchantmentPunchType() {
        super(new Identifier("minecraft:punch"), 20, 2, Rarity.RARE, ApplicableType.BOW);
    }

    /**
     * Returns the inclusive lower table-power bound, calculated as
     * {@code level * 20 - 8}.
     */
    @Override
    public int getMinModifiedLevel(int level) {
        return level * 20 - 8;
    }

    /**
     * Returns the inclusive upper table-power bound, calculated as
     * {@code getMinModifiedLevel(level) + 25}.
     */
    @Override
    public int getMaxModifiedLevel(int level) {
        return getMinModifiedLevel(level) + 25;
    }
}
