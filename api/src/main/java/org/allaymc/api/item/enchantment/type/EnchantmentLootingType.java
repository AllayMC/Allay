package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentLootingType extends EnchantmentType {
    public EnchantmentLootingType() {
        super(new Identifier("minecraft:looting"), 14, 3, Rarity.RARE);
    }
}
