package org.allaymc.server.item.enchantment;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code EnchantmentKnockbackType} represents the built-in Knockback
 * enchantment's applicability and table-power rules. Its constructor registers
 * the type and configures {@code ApplicableType.SWORD}; the table option
 * generator uses the overridden power bounds when considering each normal
 * enchantment level.
 *
 * @author daoge_cmd
 */
public class EnchantmentKnockbackType extends EnchantmentType {
    /**
     * Creates and registers the built-in Knockback enchantment with numeric ID
     * {@code 12}. The type uses {@code Rarity.UNCOMMON}, a normal maximum of
     * {@code 2}, and the {@code ApplicableType.SWORD} item category.
     */
    public EnchantmentKnockbackType() {
        super(new Identifier("minecraft:knockback"), 12, 2, Rarity.UNCOMMON, ApplicableType.SWORD);
    }

    /**
     * Returns the inclusive lower table-power bound, calculated as
     * {@code level * 20 - 15}.
     */
    @Override
    public int getMinModifiedLevel(int level) {
        return level * 20 - 15;
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
