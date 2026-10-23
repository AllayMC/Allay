package org.allaymc.api.item.fishing;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code FishingLoot} describes a registered fishing catch with an identifier,
 * category, relative weight, and item factory. Selection groups entries by
 * category before drawing a weighted entry, while the chosen factory supplies
 * the stack handed to the fishing outcome event.
 *
 * @author daoge_cmd
 */
public interface FishingLoot {
    /**
     * Returns the identifier used to register this fishing-loot definition.
     */
    Identifier getIdentifier();

    /**
     * Returns the category used to group this weighted loot entry.
     */
    FishingLootCategory getCategory();

    /**
     * Returns this entry's relative weight within its fishing category. Larger
     * positive weights increase selection share; the selector reads registered
     * values directly and falls back to cod for a nonpositive category total.
     */
    float getWeight();

    /**
     * Creates the item stack supplied when this entry is selected as a catch.
     * Implementations provide a fresh stack for independent catches, because
     * fishing events and dropped-item handling can mutate that stack.
     */
    ItemStack createItemStack();

    /**
     * Returns whether this definition declares an open-water requirement,
     * defaulting to treasure categories. {@code FishingLootTable} currently
     * checks category selection rather than this property, so overriding the
     * property does not change that selector's filtering.
     */
    default boolean requiresOpenWater() {
        return getCategory() == FishingLootCategory.TREASURE;
    }
}
