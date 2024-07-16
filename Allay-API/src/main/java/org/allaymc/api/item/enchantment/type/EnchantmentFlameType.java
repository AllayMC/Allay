package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentFlameType extends AbstractEnchantmentType {
    public static final EnchantmentFlameType FLAME = new EnchantmentFlameType();

    private EnchantmentFlameType() {
        super(new Identifier("minecraft:flame"), 21, 1, Rarity.RARE);
    }
}
