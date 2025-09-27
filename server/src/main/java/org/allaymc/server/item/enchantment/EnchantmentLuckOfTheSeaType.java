package org.allaymc.server.item.enchantment;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentLuckOfTheSeaType extends EnchantmentType {
    public EnchantmentLuckOfTheSeaType() {
        super(new Identifier("minecraft:luck_of_the_sea"), 23, 3, Rarity.RARE, ApplicableType.FISHING_ROD);
    }

    @Override
    public int getMinModifiedLevel(int level) {
        return level * 9 + 6;
    }

    @Override
    public int getMaxModifiedLevel(int level) {
        return level * 10 + 51;
    }
}
