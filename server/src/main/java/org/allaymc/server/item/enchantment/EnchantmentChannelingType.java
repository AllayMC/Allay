package org.allaymc.server.item.enchantment;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentChannelingType extends EnchantmentType {
    public EnchantmentChannelingType() {
        super(new Identifier("minecraft:channeling"), 32, 1, Rarity.VERY_RARE, ApplicableType.TRIDENT);
    }

    @Override
    public boolean isIncompatibleWith(EnchantmentType other) {
        return other instanceof EnchantmentRiptideType;
    }

    @Override
    public int getMinModifiedLevel(int level) {
        return 25;
    }

    @Override
    public int getMaxModifiedLevel(int level) {
        return 50;
    }
}
