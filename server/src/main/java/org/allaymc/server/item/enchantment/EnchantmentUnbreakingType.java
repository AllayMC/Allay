package org.allaymc.server.item.enchantment;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code EnchantmentUnbreakingType} represents the built-in Unbreaking
 * enchantment's applicability and table-power rules. Its constructor registers
 * the type and configures {@code ApplicableType.DAMAGEABLE}; the table option
 * generator uses the overridden power bounds when considering each normal
 * enchantment level.
 *
 * @author daoge_cmd
 */
public class EnchantmentUnbreakingType extends EnchantmentType {
    /**
     * Creates and registers the built-in Unbreaking enchantment with numeric ID
     * {@code 17}. The type uses {@code Rarity.UNCOMMON}, a normal maximum of
     * {@code 3}, and the {@code ApplicableType.DAMAGEABLE} item category.
     */
    public EnchantmentUnbreakingType() {
        super(new Identifier("minecraft:unbreaking"), 17, 3, Rarity.UNCOMMON, ApplicableType.DAMAGEABLE);
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
     * {@code level * 10 + 51}.
     */
    @Override
    public int getMaxModifiedLevel(int level) {
        return level * 10 + 51;
    }
}
