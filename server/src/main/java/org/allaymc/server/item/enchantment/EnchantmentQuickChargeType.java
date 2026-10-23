package org.allaymc.server.item.enchantment;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code EnchantmentQuickChargeType} represents the built-in Quick Charge
 * enchantment's applicability and table-power rules. Its constructor registers
 * the type and configures {@code ApplicableType.CROSSBOW}; the table option
 * generator uses the overridden power bounds when considering each normal
 * enchantment level.
 *
 * @author daoge_cmd
 */
public class EnchantmentQuickChargeType extends EnchantmentType {
    /**
     * Creates and registers the built-in Quick Charge enchantment with numeric
     * ID {@code 35}. The type uses {@code Rarity.UNCOMMON}, a normal maximum of
     * {@code 3}, and the {@code ApplicableType.CROSSBOW} item category.
     */
    public EnchantmentQuickChargeType() {
        super(new Identifier("minecraft:quick_charge"), 35, 3, Rarity.UNCOMMON, ApplicableType.CROSSBOW);
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
     * {@code getMinModifiedLevel(level) + 50}.
     */
    @Override
    public int getMaxModifiedLevel(int level) {
        return getMinModifiedLevel(level) + 50;
    }
}
