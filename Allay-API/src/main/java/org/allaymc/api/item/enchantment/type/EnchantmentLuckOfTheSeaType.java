package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentLuckOfTheSeaType extends AbstractEnchantmentType {
    public EnchantmentLuckOfTheSeaType() {
        super(new Identifier("minecraft:luck_of_the_sea"), 23, 3, Rarity.RARE);
    }
}
