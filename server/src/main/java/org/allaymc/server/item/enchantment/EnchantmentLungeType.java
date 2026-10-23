package org.allaymc.server.item.enchantment;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code EnchantmentLungeType} represents the built-in Lunge enchantment's
 * applicability and table-power rules. Its constructor registers the type and
 * configures {@code ApplicableType.SPEAR}; the table option generator uses the
 * overridden power bounds when considering each normal enchantment level.
 *
 * @author daoge_cmd
 */
public class EnchantmentLungeType extends EnchantmentType {
    /**
     * Creates and registers the built-in Lunge enchantment with numeric ID
     * {@code 41}. The type uses {@code Rarity.UNCOMMON}, a normal maximum of
     * {@code 3}, and the {@code ApplicableType.SPEAR} item category.
     */
    public EnchantmentLungeType() {
        super(new Identifier("minecraft:lunge"), 41, 3, Rarity.UNCOMMON, ApplicableType.SPEAR);
    }

    /**
     * Returns the inclusive lower table-power bound, calculated as
     * {@code level * 8 - 3}.
     */
    @Override
    public int getMinModifiedLevel(int level) {
        return level * 8 - 3;
    }

    /**
     * Returns the inclusive upper table-power bound, calculated as
     * {@code level * 8 + 17}.
     */
    @Override
    public int getMaxModifiedLevel(int level) {
        return level * 8 + 17;
    }
}