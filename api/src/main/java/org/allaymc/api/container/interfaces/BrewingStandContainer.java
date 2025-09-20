package org.allaymc.api.container.interfaces;

import org.allaymc.api.item.ItemStack;
import org.jetbrains.annotations.Range;

/**
 * BrewingStandContainer represents a container for a brewing stand block.
 *
 * @author daoge_cmd
 */
public interface BrewingStandContainer extends BlockContainer {
    /**
     * The constant representing the reagent slot index in the brewing stand container.
     */
    int REAGENT_SLOT = 0;

    /**
     * The constant representing the fuel slot index in the brewing stand container.
     */
    int FUEL_SLOT = 4;

    /**
     * Gets the item in the reagent slot of the brewing stand.
     *
     * @return the ItemStack in the reagent slot
     */
    default ItemStack getReagent() {
        return getItemStack(REAGENT_SLOT);
    }

    /**
     * Sets the item in the reagent slot of the brewing stand.
     *
     * @param itemStack the ItemStack to set in the reagent slot
     */
    default void setReagent(ItemStack itemStack) {
        setItemStack(REAGENT_SLOT, itemStack);
    }

    /**
     * Gets the item in the fuel slot of the brewing stand.
     *
     * @return the ItemStack in the fuel slot
     */
    default ItemStack getFuel() {
        return getItemStack(FUEL_SLOT);
    }

    /**
     * Sets the item in the fuel slot of the brewing stand.
     *
     * @param itemStack the ItemStack to set in the fuel slot
     */
    default void setFuel(ItemStack itemStack) {
        setItemStack(FUEL_SLOT, itemStack);
    }

    /**
     * Gets the item in one of the result slots of the brewing stand.
     *
     * @param slot the result slot index (0-2)
     * @return the ItemStack in the specified result slot
     */
    default ItemStack getResult(@Range(from = 0, to = 2) int slot) {
        return getItemStack(slot + 1);
    }

    /**
     * Sets the item in one of the result slots of the brewing stand.
     *
     * @param slot      the result slot index (0-2)
     * @param itemStack the ItemStack to set in the specified result slot
     */
    default void setResult(@Range(from = 0, to = 2) int slot, ItemStack itemStack) {
        setItemStack(slot + 1, itemStack);
    }
}
