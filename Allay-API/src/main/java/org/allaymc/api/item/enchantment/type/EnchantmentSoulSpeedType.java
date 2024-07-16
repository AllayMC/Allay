package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentSoulSpeedType extends AbstractEnchantmentType {
    public static final EnchantmentSoulSpeedType SOUL_SPEED = new EnchantmentSoulSpeedType();

    private EnchantmentSoulSpeedType() {
        super(new Identifier("minecraft:soul_speed"), 36, 3, Rarity.VERY_RARE);
    }
}
