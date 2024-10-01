package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentSharpnessType extends EnchantmentType {
    public EnchantmentSharpnessType() {
        super(new Identifier("minecraft:sharpness"), 9, 5, Rarity.COMMON);
    }

    @Override
    public boolean isIncompatibleWith(EnchantmentType other) {
        return other instanceof EnchantmentBaneOfArthropodsType || other instanceof EnchantmentSmiteType;
    }

    @Override
    public int getMinModifiedLevel(int level) {
        return level * 11 - 10;
    }

    @Override
    public int getMaxModifiedLevel(int level) {
        return getMinModifiedLevel(level) + 20;
    }
}
