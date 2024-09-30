package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentWindBurstType extends EnchantmentType {
    public EnchantmentWindBurstType() {
        super(new Identifier("minecraft:wind_burst"), 38, 4, Rarity.RARE);
    }

    @Override
    public boolean isAvailableInEnchantTable() {
        return false;
    }
}
