package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentMultishotType extends EnchantmentType {
    public EnchantmentMultishotType() {
        super(new Identifier("minecraft:multishot"), 33, 1, Rarity.RARE);
    }

    @Override
    public boolean isIncompatibleWith(EnchantmentType other) {
        return other instanceof EnchantmentPiercingType;
    }
}
