package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentPowerType extends EnchantmentType {
    public EnchantmentPowerType() {
        super(new Identifier("minecraft:power"), 19, 5, Rarity.COMMON);
    }
}
