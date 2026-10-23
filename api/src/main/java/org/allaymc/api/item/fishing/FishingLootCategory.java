package org.allaymc.api.item.fishing;

/**
 * {@code FishingLootCategory} groups fishing entries before weighted item
 * selection. {@link FishingLootTable} starts with fish, treasure, and junk
 * probabilities, adjusts them using enchantment levels, and redirects a
 * treasure-category roll to junk when open water is absent.
 *
 * @author daoge_cmd
 */
public enum FishingLootCategory {
    /**
     * Fish catch category.
     */
    FISH,

    /**
     * Open-water treasure category.
     */
    TREASURE,

    /**
     * Junk catch category.
     */
    JUNK
}
