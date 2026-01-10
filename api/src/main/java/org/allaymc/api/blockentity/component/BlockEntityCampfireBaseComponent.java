package org.allaymc.api.blockentity.component;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.recipe.FurnaceRecipe;

/**
 * @author daoge_cmd
 */
public interface BlockEntityCampfireBaseComponent extends BlockEntityBaseComponent {
    /**
     * The cooking time for campfire recipes in ticks (30 seconds).
     */
    int COOK_TIME = 600;

    /**
     * Gets the furnace recipe type for this campfire.
     *
     * @return the furnace recipe type
     */
    FurnaceRecipe.Type getFurnaceRecipeType();

    /**
     * Gets the item stack in the specified slot.
     *
     * @param slot the slot index (0-3)
     * @return the item stack in the slot
     */
    ItemStack getItemStack(int slot);

    /**
     * Sets the item stack in the specified slot.
     *
     * @param slot the slot index (0-3)
     * @param item the item stack to set
     */
    void setItemStack(int slot, ItemStack item);

    /**
     * Gets the remaining cook time for the item in the specified slot.
     *
     * @param slot the slot index (0-3)
     * @return the remaining cook time in ticks
     */
    int getCookTime(int slot);

    /**
     * Sets the remaining cook time for the item in the specified slot.
     *
     * @param slot     the slot index (0-3)
     * @param cookTime the cook time in ticks
     */
    void setCookTime(int slot, int cookTime);

    /**
     * Checks if the campfire can accept the item.
     *
     * @param item the item to check
     * @return true if the campfire can accept the item
     */
    boolean canAcceptItemStack(ItemStack item);

    /**
     * Tries to add an item to the campfire.
     *
     * @param item the item to add
     * @return true if the item was added successfully
     */
    boolean tryAddItemStack(ItemStack item);
}
