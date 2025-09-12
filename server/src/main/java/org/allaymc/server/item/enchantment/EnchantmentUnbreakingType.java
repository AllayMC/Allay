package org.allaymc.server.item.enchantment;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentUnbreakingType extends EnchantmentType {
    public EnchantmentUnbreakingType() {
        super(new Identifier("minecraft:unbreaking"), 17, 3, Rarity.UNCOMMON, ApplicableType.DAMAGEABLE);
    }

    @Override
    public int getMinModifiedLevel(int level) {
        return level * 8 - 3;
    }

    @Override
    public int getMaxModifiedLevel(int level) {
        return level * 10 + 51;
    }
}
