package org.allaymc.api.item.enchantment;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * {@code Rarity} associates enchantment rarity categories with table-selection
 * weights and anvil costs. Higher weights make a category more likely during
 * weighted selection, while {@code fromWeight} maps arbitrary integers onto
 * these four categories using fixed boundaries.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public enum Rarity {
    /**
     * Common enchantment rarity category.
     */
    COMMON(10, 1),
    /**
     * Uncommon enchantment rarity category.
     */
    UNCOMMON(5, 2),
    /**
     * Rare enchantment rarity category.
     */
    RARE(2, 4),
    /**
     * Very rare enchantment category.
     */
    VERY_RARE(1, 8);

    /**
     * Relative weighted enchantment selection frequency.
     */
    private final int weight;
    /**
     * Anvil cost associated with this rarity.
     */
    private final int anvilCost;

    /**
     * Returns the rarity selected by the configured weight boundaries. Values
     * below {@code 2}, including negatives, select {@code VERY_RARE}; values
     * below {@code 5} select {@code RARE}, below {@code 10} select
     * {@code UNCOMMON}, and all others select {@code COMMON}.
     */
    public static Rarity fromWeight(int weight) {
        // These boundaries follow the declared rarity weights. Values outside
        // their range stay in an endpoint category instead of rejecting the input.
        if (weight < 2) return VERY_RARE;
        else if (weight < 5) return RARE;
        else if (weight < 10) return UNCOMMON;
        return COMMON;
    }
}
