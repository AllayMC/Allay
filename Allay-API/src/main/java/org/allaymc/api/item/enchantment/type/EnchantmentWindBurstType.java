package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentWindBurstType extends AbstractEnchantmentType {
    public EnchantmentWindBurstType() {
        super(new Identifier("minecraft:wind_burst"), 38, 4, Rarity.RARE);
    }
}
