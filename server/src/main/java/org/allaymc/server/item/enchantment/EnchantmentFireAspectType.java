package org.allaymc.server.item.enchantment;

import org.allaymc.api.item.enchantment.ApplicableType;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.Rarity;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * @author daoge_cmd
 */
public class EnchantmentFireAspectType extends EnchantmentType {
    public EnchantmentFireAspectType() {
        super(new Identifier("minecraft:fire_aspect"), 13, 2, Rarity.RARE, ApplicableType.SWORD);
    }

    @Override
    public int getMinModifiedLevel(int level) {
        return switch (level) {
            case 1 -> 10;
            case 2 -> 30;
            default -> 0;
        };
    }

    @Override
    public int getMaxModifiedLevel(int level) {
        return switch (level) {
            case 1 -> 61;
            case 2 -> 71;
            default -> 0;
        };
    }
}
