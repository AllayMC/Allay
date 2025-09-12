package org.allaymc.server.item.enchantment;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentImpalingType extends EnchantmentType {
    public EnchantmentImpalingType() {
        super(new Identifier("minecraft:impaling"), 29, 5, Rarity.RARE, ApplicableType.TRIDENT);
    }

    @Override
    public int getMinModifiedLevel(int level) {
        return level * 8 - 7;
    }

    @Override
    public int getMaxModifiedLevel(int level) {
        return getMinModifiedLevel(level) + 20;
    }
}
