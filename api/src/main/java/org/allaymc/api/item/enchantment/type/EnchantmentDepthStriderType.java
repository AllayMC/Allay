package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentDepthStriderType extends EnchantmentType {
    public EnchantmentDepthStriderType() {
        super(new Identifier("minecraft:depth_strider"), 7, 3, Rarity.RARE);
    }

    @Override
    public boolean isIncompatibleWith(EnchantmentType other) {
        return other instanceof EnchantmentFrostWalkerType;
    }

    @Override
    public int getMinModifiedLevel(int level) {
        return level * 10;
    }

    @Override
    public int getMaxModifiedLevel(int level) {
        return level * 100;
    }
}
