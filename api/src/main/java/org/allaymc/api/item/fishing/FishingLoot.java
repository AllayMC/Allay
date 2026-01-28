package org.allaymc.api.item.fishing;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * Represents a fishing loot entry that can be obtained when fishing.
 *
 * @author daoge_cmd
 */
public interface FishingLoot {
    /**
     * Gets the unique identifier of this fishing loot.
     *
     * @return the identifier
     */
    Identifier getIdentifier();

    /**
     * Gets the category of this fishing loot.
     *
     * @return the category
     */
    FishingLootCategory getCategory();

    /**
     * Gets the weight of this loot within its category.
     * Higher weight means higher chance to be selected.
     *
     * @return the weight
     */
    float getWeight();

    /**
     * Creates a new item stack for this loot.
     * This method is called each time the loot is selected.
     *
     * @return the item stack
     */
    ItemStack createItemStack();

    /**
     * Checks if this loot requires open water to be caught.
     * Treasure items require open water in vanilla Minecraft.
     *
     * @return {@code true} if open water is required
     */
    default boolean requiresOpenWater() {
        return getCategory() == FishingLootCategory.TREASURE;
    }
}
