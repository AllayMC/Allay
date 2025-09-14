package org.allaymc.api.container.interfaces;

import org.allaymc.api.item.ItemStack;

/**
 * CartographyTableContainer represents a container for a cartography table block.
 *
 * @author daoge_cmd
 */
public interface CartographyTableContainer extends BlockContainer {
    /**
     * The constant representing the input slot index in the cartography table container.
     */
    int INPUT_SLOT = 0;

    /**
     * The constant representing the additional slot index in the cartography table container.
     */
    int ADDITIONAL_SLOT = 1;

    /**
     * The constant representing the result slot index in the cartography table container.
     */
    int RESULT_SLOT = 2;

    /**
     * Gets the item in the input slot of the cartography table.
     *
     * @return the ItemStack in the input slot
     */
    default ItemStack getInput() {
        return getItemStack(INPUT_SLOT);
    }

    /**
     * Sets the item in the input slot of the cartography table.
     *
     * @param itemStack the ItemStack to set in the input slot
     */
    default void setInput(ItemStack itemStack) {
        setItemStack(INPUT_SLOT, itemStack);
    }

    /**
     * Gets the item in the additional slot of the cartography table.
     *
     * @return the ItemStack in the additional slot
     */
    default ItemStack getAdditional() {
        return getItemStack(ADDITIONAL_SLOT);
    }

    /**
     * Sets the item in the additional slot of the cartography table.
     *
     * @param itemStack the ItemStack to set in the additional slot
     */
    default void setAdditional(ItemStack itemStack) {
        setItemStack(ADDITIONAL_SLOT, itemStack);
    }

    /**
     * Gets the item in the result slot of the cartography table.
     *
     * @return the ItemStack in the result slot
     */
    default ItemStack getResult() {
        return getItemStack(RESULT_SLOT);
    }

    /**
     * Sets the item in the result slot of the cartography table.
     *
     * @param itemStack the ItemStack to set in the result slot
     */
    default void setResult(ItemStack itemStack) {
        setItemStack(RESULT_SLOT, itemStack);
    }
}
