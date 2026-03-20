package org.allaymc.api.container.interfaces;

import org.allaymc.api.item.ItemStack;

/**
 * StonecutterContainer represents a container for a stonecutter block.
 *
 * @author daoge_cmd
 */
public interface StonecutterContainer extends BlockContainer, RecipeContainer {
    /**
     * The constant representing the input slot index in the stonecutter container.
     */
    int INPUT_SLOT = 0;

    /**
     * The constant representing the result slot index in the stonecutter container.
     */
    int RESULT_SLOT = 1;

    /**
     * Gets the item in the input slot of the stonecutter.
     *
     * @return the ItemStack in the input slot
     */
    default ItemStack getInput() {
        return getItemStack(INPUT_SLOT);
    }

    /**
     * Sets the item in the input slot of the stonecutter.
     *
     * @param itemStack the ItemStack to set in the input slot
     */
    default void setInput(ItemStack itemStack) {
        setItemStack(INPUT_SLOT, itemStack);
    }

    /**
     * Gets the item in the result slot of the stonecutter.
     *
     * @return the ItemStack in the result slot
     */
    default ItemStack getResult() {
        return getItemStack(RESULT_SLOT);
    }

    /**
     * Sets the item in the result slot of the stonecutter.
     *
     * @param itemStack the ItemStack to set in the result slot
     */
    default void setResult(ItemStack itemStack) {
        setItemStack(RESULT_SLOT, itemStack);
    }
}
