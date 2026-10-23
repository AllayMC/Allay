package org.allaymc.server.item.enchantment;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code EnchantmentThornsType} represents the built-in Thorns enchantment's
 * applicability and table-power rules. Its constructor registers the type and
 * configures {@code ApplicableType.ARMOR_WITHOUT_ELYTRA}; the table option
 * generator uses the overridden power bounds when considering each normal
 * enchantment level.
 *
 * @author daoge_cmd
 */
public class EnchantmentThornsType extends EnchantmentType {
    /**
     * Creates and registers the built-in Thorns enchantment with numeric ID
     * {@code 5}. The type uses {@code Rarity.VERY_RARE}, a normal maximum of
     * {@code 3}, and the {@code ApplicableType.ARMOR_WITHOUT_ELYTRA} item
     * category.
     */
    public EnchantmentThornsType() {
        super(new Identifier("minecraft:thorns"), 5, 3, Rarity.VERY_RARE, ApplicableType.ARMOR_WITHOUT_ELYTRA);
    }

    /**
     * Returns the inclusive lower table-power bound, calculated as
     * {@code level * 20 - 10}.
     */
    @Override
    public int getMinModifiedLevel(int level) {
        return level * 20 - 10;
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
