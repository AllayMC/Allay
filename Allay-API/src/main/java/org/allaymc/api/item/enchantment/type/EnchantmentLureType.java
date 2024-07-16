package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentLureType extends AbstractEnchantmentType {
    public EnchantmentLureType() {
        super(new Identifier("minecraft:lure"), 24, 3, Rarity.RARE);
    }
}
