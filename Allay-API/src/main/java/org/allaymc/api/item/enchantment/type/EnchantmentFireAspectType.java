package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentFireAspectType extends EnchantmentType {
    public EnchantmentFireAspectType() {
        super(new Identifier("minecraft:fire_aspect"), 13, 2, Rarity.RARE);
    }
}
