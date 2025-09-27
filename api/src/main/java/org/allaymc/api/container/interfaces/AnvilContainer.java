package org.allaymc.api.container.interfaces;

import org.allaymc.api.item.ItemStack;

/**
 * AnvilContainer represents a container for an anvil block.
 *
 * @author daoge_cmd
 */
public interface AnvilContainer extends BlockContainer {
    /**
     * The constant representing the input slot index in the anvil container.
     */
    int INPUT_SLOT = 0;

    /**
     * The constant representing the material slot index in the anvil container.
     */
    int MATERIAL_SLOT = 1;

    /**
     * The constant representing the result slot index in the anvil container.
     */
    int RESULT_SLOT = 2;

    /**
     * Gets the item in the input slot of the anvil.
     *
     * @return the ItemStack in the input slot
     */
    default ItemStack getInput() {
        return getItemStack(INPUT_SLOT);
    }

    /**
     * Sets the item in the input slot of the anvil.
     *
     * @param itemStack the ItemStack to set in the input slot
     */
    default void setInput(ItemStack itemStack) {
        setItemStack(INPUT_SLOT, itemStack);
    }

    /**
     * Gets the item in the material slot of the anvil.
     *
     * @return the ItemStack in the material slot
     */
    default ItemStack getMaterial() {
        return getItemStack(MATERIAL_SLOT);
    }

    /**
     * Sets the item in the material slot of the anvil.
     *
     * @param itemStack the ItemStack to set in the material slot
     */
    default void setMaterial(ItemStack itemStack) {
        setItemStack(MATERIAL_SLOT, itemStack);
    }

    /**
     * Gets the item in the result slot of the anvil.
     *
     * @return the ItemStack in the result slot
     */
    default ItemStack getResult() {
        return getItemStack(RESULT_SLOT);
    }

    /**
     * Sets the item in the result slot of the anvil.
     *
     * @param itemStack the ItemStack to set in the result slot
     */
    default void setResult(ItemStack itemStack) {
        setItemStack(RESULT_SLOT, itemStack);
    }
}
