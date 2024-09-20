package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentLuckOfTheSeaType extends EnchantmentType {
    public EnchantmentLuckOfTheSeaType() {
        super(new Identifier("minecraft:luck_of_the_sea"), 23, 3, Rarity.RARE);
    }
}
