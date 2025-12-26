package org.allaymc.api.container.interfaces;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.item.type.ItemTypes;
import org.jetbrains.annotations.Range;

/**
 * BrewingStandContainer represents a container for a brewing stand block.
 *
 * @author daoge_cmd
 */
public interface BrewingStandContainer extends BlockContainer, SidedContainer {
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

    @Override
    default int[] getAllowedInsertSlots(BlockFace side, ItemStack stack) {
        if (side == BlockFace.UP) {
            return isBrewingReagent(stack) ? new int[]{REAGENT_SLOT} : new int[0];
        }
        if (side == BlockFace.DOWN) {
            return new int[0];
        }
        if (isBrewingFuel(stack)) {
            return new int[]{FUEL_SLOT};
        }
        if (isBrewingBottle(stack)) {
            return new int[]{1, 2, 3};
        }
        return new int[0];
    }

    @Override
    default int[] getAllowedExtractSlots(BlockFace side) {
        if (side == BlockFace.DOWN) {
            return new int[]{1, 2, 3};
        }
        return new int[0];
    }

    private static boolean isBrewingFuel(ItemStack stack) {
        if (stack == null || stack == ItemAirStack.AIR_STACK) {
            return false;
        }
        return stack.getItemType() == ItemTypes.BLAZE_POWDER;
    }

    private static boolean isBrewingBottle(ItemStack stack) {
        if (stack == null || stack == ItemAirStack.AIR_STACK) {
            return false;
        }
        var type = stack.getItemType();
        return type == ItemTypes.GLASS_BOTTLE ||
                type == ItemTypes.POTION ||
                type == ItemTypes.SPLASH_POTION ||
                type == ItemTypes.LINGERING_POTION;
    }

    private static boolean isBrewingReagent(ItemStack stack) {
        return !isBrewingFuel(stack) && !isBrewingBottle(stack);
    }
}
