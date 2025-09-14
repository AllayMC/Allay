package org.allaymc.api.container.interfaces;

import org.allaymc.api.item.ItemStack;

/**
 * LoomContainer represents a container for a loom block.
 *
 * @author daoge_cmd
 */
public interface LoomContainer extends BlockContainer {
    /**
     * The constant representing the input slot index in the loom container.
     */
    int INPUT_SLOT = 0;

    /**
     * The constant representing the dye slot index in the loom container.
     */
    int DYE_SLOT = 1;

    /**
     * The constant representing the material slot index in the loom container.
     */
    int MATERIAL_SLOT = 2;

    /**
     * The constant representing the result slot index in the loom container.
     */
    int RESULT_SLOT = 3;

    /**
     * Gets the item in the input slot of the loom.
     *
     * @return the ItemStack in the input slot
     */
    default ItemStack getInput() {
        return getItemStack(INPUT_SLOT);
    }

    /**
     * Sets the item in the input slot of the loom.
     *
     * @param itemStack the ItemStack to set in the input slot
     */
    default void setInput(ItemStack itemStack) {
        setItemStack(INPUT_SLOT, itemStack);
    }

    /**
     * Gets the item in the dye slot of the loom.
     *
     * @return the ItemStack in the dye slot
     */
    default ItemStack getDye() {
        return getItemStack(DYE_SLOT);
    }

    /**
     * Sets the item in the dye slot of the loom.
     *
     * @param itemStack the ItemStack to set in the dye slot
     */
    default void setDye(ItemStack itemStack) {
        setItemStack(DYE_SLOT, itemStack);
    }

    /**
     * Gets the item in the material slot of the loom.
     *
     * @return the ItemStack in the material slot
     */
    default ItemStack getMaterial() {
        return getItemStack(MATERIAL_SLOT);
    }

    /**
     * Sets the item in the material slot of the loom.
     *
     * @param itemStack the ItemStack to set in the material slot
     */
    default void setMaterial(ItemStack itemStack) {
        setItemStack(MATERIAL_SLOT, itemStack);
    }

    /**
     * Gets the item in the result slot of the loom.
     *
     * @return the ItemStack in the result slot
     */
    default ItemStack getResult() {
        return getItemStack(RESULT_SLOT);
    }

    /**
     * Sets the item in the result slot of the loom.
     *
     * @param itemStack the ItemStack to set in the result slot
     */
    default void setResult(ItemStack itemStack) {
        setItemStack(RESULT_SLOT, itemStack);
    }
}
