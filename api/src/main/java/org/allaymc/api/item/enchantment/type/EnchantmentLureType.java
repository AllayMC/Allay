package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentLureType extends EnchantmentType {
    public EnchantmentLureType() {
        super(new Identifier("minecraft:lure"), 24, 3, Rarity.RARE);
    }
}
