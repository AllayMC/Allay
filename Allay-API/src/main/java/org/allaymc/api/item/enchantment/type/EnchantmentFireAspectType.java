package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentFireAspectType extends AbstractEnchantmentType {
    public static final EnchantmentFireAspectType FIRE_ASPECT = new EnchantmentFireAspectType();

    private EnchantmentFireAspectType() {
        super(new Identifier("minecraft:fire_aspect"), 13, 2, Rarity.RARE);
    }
}
