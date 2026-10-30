package org.allaymc.server.item.enchantment;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code EnchantmentLuckOfTheSeaType} represents the built-in Luck Of The Sea
 * enchantment's applicability and table-power rules. Its constructor registers
 * the type and configures {@code ApplicableType.FISHING_ROD}; the table option
 * generator uses the overridden power bounds when considering each normal
 * enchantment level.
 *
 * @author daoge_cmd
 */
public class EnchantmentLuckOfTheSeaType extends EnchantmentType {
    /**
     * Creates and registers the built-in Luck Of The Sea enchantment with
     * numeric ID {@code 23}. The type uses {@code Rarity.RARE}, a normal
     * maximum of {@code 3}, and the {@code ApplicableType.FISHING_ROD} item
     * category.
     */
    public EnchantmentLuckOfTheSeaType() {
        super(new Identifier("minecraft:luck_of_the_sea"), 23, 3, Rarity.RARE, ApplicableType.FISHING_ROD);
    }

    /**
     * Returns the inclusive lower table-power bound, calculated as
     * {@code level * 9 + 6}.
     */
    @Override
    public int getMinModifiedLevel(int level) {
        return level * 9 + 6;
    }

    /**
     * Returns the inclusive upper table-power bound, calculated as
     * {@code level * 10 + 51}.
     */
    @Override
    public int getMaxModifiedLevel(int level) {
        return level * 10 + 51;
    }
}
