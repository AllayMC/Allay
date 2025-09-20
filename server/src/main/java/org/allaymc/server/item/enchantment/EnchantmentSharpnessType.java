package org.allaymc.server.item.enchantment;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentSharpnessType extends EnchantmentType {
    public EnchantmentSharpnessType() {
        super(new Identifier("minecraft:sharpness"), 9, 5, Rarity.COMMON, ApplicableType.SWORD_OR_AXE);
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
