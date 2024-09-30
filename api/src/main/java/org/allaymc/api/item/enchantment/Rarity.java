package org.allaymc.api.item.enchantment;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents the rarity of an enchantment.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public enum Rarity {
    COMMON(10),
    UNCOMMON(5),
    RARE(2),
    VERY_RARE(1);

    private final int weight;

    /**
     * Converts the weight to the closest rarity using floor semantic.
     *
     * @param weight The enchantment weight.
     *
     * @return The closest rarity.
     */
    public static Rarity fromWeight(int weight) {
        if (weight < 2) return VERY_RARE;
        else if (weight < 5) return RARE;
        else if (weight < 10) return UNCOMMON;
        return COMMON;
    }
}
