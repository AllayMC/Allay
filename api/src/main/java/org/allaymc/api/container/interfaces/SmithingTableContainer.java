package org.allaymc.api.container.interfaces;

import org.allaymc.api.item.ItemStack;

/**
 * SmithingTableContainer represents a container for a smithing table block.
 *
 * @author daoge_cmd
 */
public interface SmithingTableContainer extends BlockContainer, RecipeContainer {
    /**
     * The constant representing the input slot index in the smithing table container.
     */
    int INPUT_SLOT = 0;

    /**
     * The constant representing the material slot index in the smithing table container.
     */
    int MATERIAL_SLOT = 1;

    /**
     * The constant representing the template slot index in the smithing table container.
     */
    int TEMPLATE_SLOT = 2;

    /**
     * The constant representing the result slot index in the smithing table container.
     */
    int RESULT_SLOT = 3;

    /**
     * Gets the item in the input slot of the smithing table.
     *
     * @return the ItemStack in the input slot
     */
    default ItemStack getInput() {
        return getItemStack(INPUT_SLOT);
    }

    /**
     * Sets the item in the input slot of the smithing table.
     *
     * @param itemStack the ItemStack to set in the input slot
     */
    default void setInput(ItemStack itemStack) {
        setItemStack(INPUT_SLOT, itemStack);
    }

    /**
     * Gets the item in the material slot of the smithing table.
     *
     * @return the ItemStack in the material slot
     */
    default ItemStack getMaterial() {
        return getItemStack(MATERIAL_SLOT);
    }

    /**
     * Sets the item in the material slot of the smithing table.
     *
     * @param itemStack the ItemStack to set in the material slot
     */
    default void setMaterial(ItemStack itemStack) {
        setItemStack(MATERIAL_SLOT, itemStack);
    }

    /**
     * Gets the item in the template slot of the smithing table.
     *
     * @return the ItemStack in the template slot
     */
    default ItemStack getTemplate() {
        return getItemStack(TEMPLATE_SLOT);
    }

    /**
     * Sets the item in the template slot of the smithing table.
     *
     * @param itemStack the ItemStack to set in the template slot
     */
    default void setTemplate(ItemStack itemStack) {
        setItemStack(TEMPLATE_SLOT, itemStack);
    }

    /**
     * Gets the item in the result slot of the smithing table.
     *
     * @return the ItemStack in the result slot
     */
    default ItemStack getResult() {
        return getItemStack(RESULT_SLOT);
    }

    /**
     * Sets the item in the result slot of the smithing table.
     *
     * @param itemStack the ItemStack to set in the result slot
     */
    default void setResult(ItemStack itemStack) {
        setItemStack(RESULT_SLOT, itemStack);
    }
}
