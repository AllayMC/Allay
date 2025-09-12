package org.allaymc.server.item.enchantment;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentMultishotType extends EnchantmentType {
    public EnchantmentMultishotType() {
        super(new Identifier("minecraft:multishot"), 33, 1, Rarity.RARE, ApplicableType.CROSSBOW);
    }

    @Override
    public boolean isIncompatibleWith(EnchantmentType other) {
        return other instanceof EnchantmentPiercingType;
    }

    @Override
    public int getMinModifiedLevel(int level) {
        return 20;
    }

    @Override
    public int getMaxModifiedLevel(int level) {
        return 70;
    }
}
