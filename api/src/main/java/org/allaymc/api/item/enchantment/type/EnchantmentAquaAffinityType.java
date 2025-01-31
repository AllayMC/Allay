package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentAquaAffinityType extends EnchantmentType {
    public EnchantmentAquaAffinityType() {
        super(new Identifier("minecraft:aqua_affinity"), 8, 1, Rarity.RARE, ApplicableType.HELMET);
    }

    @Override
    public int getMinModifiedLevel(int level) {
        return 1;
    }

    @Override
    public int getMaxModifiedLevel(int level) {
        return 41;
    }
}