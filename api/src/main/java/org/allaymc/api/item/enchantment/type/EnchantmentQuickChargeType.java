package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentQuickChargeType extends EnchantmentType {
    public EnchantmentQuickChargeType() {
        super(new Identifier("minecraft:quick_charge"), 35, 3, Rarity.UNCOMMON);
    }
}
