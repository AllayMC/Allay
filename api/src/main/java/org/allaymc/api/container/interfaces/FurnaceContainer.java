package org.allaymc.api.container.interfaces;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;

/**
 * FurnaceContainer represents a container for a furnace block.
 *
 * @author daoge_cmd
 */
public interface FurnaceContainer extends BlockContainer, SidedContainer {
    /**
     * The constant representing the ingredient slot index in the furnace container.
     */
    int INGREDIENT_SLOT = 0;

    /**
     * The constant representing the fuel slot index in the furnace container.
     */
    int FUEL_SLOT = 1;

    /**
     * The constant representing the result slot index in the furnace container.
     */
    int RESULT_SLOT = 2;

    /**
     * Gets the item in the ingredient slot of the furnace.
     *
     * @return the ItemStack in the ingredient slot
     */
    default ItemStack getIngredient() {
        return getItemStack(INGREDIENT_SLOT);
    }

    /**
     * Sets the item in the ingredient slot of the furnace.
     *
     * @param itemStack the ItemStack to set in the ingredient slot
     */
    default void setIngredient(ItemStack itemStack) {
        setItemStack(INGREDIENT_SLOT, itemStack);
    }

    /**
     * Gets the item in the fuel slot of the furnace.
     *
     * @return the ItemStack in the fuel slot
     */
    default ItemStack getFuel() {
        return getItemStack(FUEL_SLOT);
    }

    /**
     * Sets the item in the fuel slot of the furnace.
     *
     * @param itemStack the ItemStack to set in the fuel slot
     */
    default void setFuel(ItemStack itemStack) {
        setItemStack(FUEL_SLOT, itemStack);
    }

    /**
     * Gets the item in the result slot of the furnace.
     *
     * @return the ItemStack in the result slot
     */
    default ItemStack getResult() {
        return getItemStack(RESULT_SLOT);
    }

    /**
     * Sets the item in the result slot of the furnace.
     *
     * @param itemStack the ItemStack to set in the result slot
     */
    default void setResult(ItemStack itemStack) {
        setItemStack(RESULT_SLOT, itemStack);
    }

    @Override
    default int[] getAllowedInsertSlots(BlockFace side, ItemStack stack) {
        if (side == BlockFace.UP) {
            return new int[]{INGREDIENT_SLOT};
        }
        if (side == BlockFace.DOWN) {
            return new int[0];
        }
        return isFuel(stack) ? new int[]{FUEL_SLOT} : new int[0];
    }

    @Override
    default int[] getAllowedExtractSlots(BlockFace side) {
        if (side == BlockFace.DOWN) {
            return new int[]{RESULT_SLOT};
        }
        return new int[0];
    }

    private static boolean isFuel(ItemStack stack) {
        if (stack == null || stack == ItemAirStack.AIR_STACK) {
            return false;
        }
        return stack.getItemType().getItemData().furnaceBurnDuration() > 0;
    }
}
