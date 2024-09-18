package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentSoulSpeedType extends AbstractEnchantmentType {
    public EnchantmentSoulSpeedType() {
        super(new Identifier("minecraft:soul_speed"), 36, 3, Rarity.VERY_RARE);
    }
}
