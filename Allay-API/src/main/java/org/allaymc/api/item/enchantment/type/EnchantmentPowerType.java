package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentPowerType extends AbstractEnchantmentType {
    public static final EnchantmentPowerType POWER = new EnchantmentPowerType();

    private EnchantmentPowerType() {
        super(new Identifier("minecraft:power"), 19, 5, Rarity.COMMON);
    }
}
