package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentPunchType extends EnchantmentType {
    public EnchantmentPunchType() {
        super(new Identifier("minecraft:punch"), 20, 2, Rarity.RARE);
    }
}
