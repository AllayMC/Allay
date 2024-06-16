package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

public class EnchantmentWindBurstType extends AbstractEnchantmentType {
    public static final EnchantmentWindBurstType WIND_BURST_TYPE = new EnchantmentWindBurstType();

    //todo max level and Rarity
    private EnchantmentWindBurstType() {
        super(new Identifier("minecraft:wind_burst"), 38, 1, Rarity.VERY_RARE);
    }
}
