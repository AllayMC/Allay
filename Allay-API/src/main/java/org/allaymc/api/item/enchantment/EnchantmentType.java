package org.allaymc.api.item.enchantment;

import org.allaymc.api.utils.Identifier;

/**
 * Allay Project 2023/10/21
 *
 * @author daoge_cmd
 */
public interface EnchantmentType {

    Identifier getIdentifier();

    int getId();

    int getMaxLevel();

    Rarity getRarity();

    EnchantmentInstance createInstance(int level);

    default boolean checkIncompatible(EnchantmentType other) {
        return false;
    }
}
