package org.allaymc.api.item.enchantment.type;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentKnockbackType extends EnchantmentType {
    public EnchantmentKnockbackType() {
        super(new Identifier("minecraft:knockback"), 12, 2, Rarity.UNCOMMON, ApplicableType.SWORD);
    }

    @Override
    public int getMinModifiedLevel(int level) {
        return level * 20 - 15;
    }

    @Override
    public int getMaxModifiedLevel(int level) {
        return level * 10 + 51;
    }
}
