package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentSwiftSneakType extends EnchantmentType {
    public EnchantmentSwiftSneakType() {
        super(new Identifier("minecraft:swift_sneak"), 37, 3, Rarity.VERY_RARE);
    }

    @Override
    public boolean isAvailableInEnchantTable() {
        return false;
    }
}
