package org.allaymc.server.item.enchantment;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code EnchantmentLootingType} represents the built-in Looting enchantment's
 * applicability and table-power rules. Its constructor registers the type and
 * configures {@code ApplicableType.SWORD}; the table option generator uses the
 * overridden power bounds when considering each normal enchantment level.
 *
 * @author daoge_cmd
 */
public class EnchantmentLootingType extends EnchantmentType {
    /**
     * Creates and registers the built-in Looting enchantment with numeric ID
     * {@code 14}. The type uses {@code Rarity.RARE}, a normal maximum of
     * {@code 3}, and the {@code ApplicableType.SWORD} item category.
     */
    public EnchantmentLootingType() {
        super(new Identifier("minecraft:looting"), 14, 3, Rarity.RARE, ApplicableType.SWORD);
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
