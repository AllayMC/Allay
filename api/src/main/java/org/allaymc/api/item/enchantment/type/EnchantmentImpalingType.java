package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentImpalingType extends EnchantmentType {
    public EnchantmentImpalingType() {
        super(new Identifier("minecraft:impaling"), 29, 5, Rarity.RARE);
    }
}
