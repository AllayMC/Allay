package org.allaymc.api.container.interfaces;

import org.allaymc.api.item.ItemStack;

/**
 * {@code EnchantTableContainer} exposes named slots for an enchanting table container.
 * Its accessors use the shared container storage and notification rules, so callers can
 * address each slot by its gameplay role without repeating numeric indices.
 *
 * @author daoge_cmd
 */
public interface EnchantTableContainer extends BlockContainer {
    /**
     * The slot constants identify enchanting table storage positions used by the named
     * accessors and container action processing. Enchantment input slot index.
     */
    int INPUT_SLOT = 0;

    /**
     * Enchantment material slot index.
     */
    int MATERIAL_SLOT = 1;

    /**
     * Returns the stored stack in the enchantment input slot. The result is shared with
     * this container; callers that change its contents must notify the slot afterward.
     */
    default ItemStack getInput() {
        return getItemStack(INPUT_SLOT);
    }

    /**
     * Returns the stored stack in the enchantment material slot. The result is shared
     * with this container; callers that change its contents must notify the slot
     * afterward.
     */
    default ItemStack getMaterial() {
        return getItemStack(MATERIAL_SLOT);
    }
}
