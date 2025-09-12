package org.allaymc.server.item.enchantment;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentFlameType extends EnchantmentType {
    public EnchantmentFlameType() {
        super(new Identifier("minecraft:flame"), 21, 1, Rarity.RARE, ApplicableType.BOW);
    }

    @Override
    public int getMinModifiedLevel(int level) {
        return 20;
    }

    @Override
    public int getMaxModifiedLevel(int level) {
        return 50;
    }
}
