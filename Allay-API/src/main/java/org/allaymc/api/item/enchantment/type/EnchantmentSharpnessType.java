package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.AbstractEnchantmentType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public class EnchantmentSharpnessType extends AbstractEnchantmentType {
    public static final EnchantmentSharpnessType SHARPNESS_TYPE = new EnchantmentSharpnessType();

    private EnchantmentSharpnessType() {
        super(new Identifier("minecraft:sharpness"), 9, 5, Rarity.COMMON);
    }

    @Override
    public boolean checkIncompatible(EnchantmentType other) {
        return other instanceof EnchantmentBaneOfArthropodsType || other instanceof EnchantmentSmiteType;
    }
}
