package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentFortuneType extends EnchantmentType {
    public EnchantmentFortuneType() {
        super(new Identifier("minecraft:fortune"), 18, 3, Rarity.RARE);
    }

    @Override
    public boolean isIncompatibleWith(EnchantmentType other) {
        return other instanceof EnchantmentSilkTouchType;
    }
}
