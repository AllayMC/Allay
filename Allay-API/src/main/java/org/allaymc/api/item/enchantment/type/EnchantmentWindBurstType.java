package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * Allay Project 2024/6/18
 *
 * @author daoge_cmd
 */
public class EnchantmentWindBurstType extends AbstractEnchantmentType {
    public static final EnchantmentWindBurstType WIND_BURST_TYPE = new EnchantmentWindBurstType();

    private EnchantmentWindBurstType() {
        super(new Identifier("minecraft:wind_burst"), 38, 4, Rarity.RARE);
    }
}
