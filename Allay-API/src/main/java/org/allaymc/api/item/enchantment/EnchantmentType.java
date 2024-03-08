package org.allaymc.api.item.enchantment;

import org.allaymc.api.common.data.Identifier;

/**
 * Allay Project 2023/10/21
 *
 * @author daoge_cmd
 */
public interface EnchantmentType {

    Identifier getIdentifier();

    short getId();

    short getMaxLevel();

    Rarity getRarity();

    EnchantmentInstance createInstance(short level);

    default boolean checkCompatibility(EnchantmentType other) {
        return true;
    }
}
