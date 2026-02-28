package org.allaymc.api.blockentity.component;

import org.allaymc.api.item.ItemStack;

/**
 * @author daoge_cmd
 */
public interface BlockEntityShelfBaseComponent extends BlockEntityBaseComponent {
    /**
     * The number of item slots in a shelf.
     */
    int SLOT_COUNT = 3;

    /**
     * Gets the item stack in the specified slot.
     *
     * @param slot the slot index (0-2)
     * @return the item stack in the slot, or air stack if the slot is empty
     */
    ItemStack getItemStack(int slot);

    /**
     * Sets the item stack in the specified slot.
     *
     * @param slot the slot index (0-2)
     * @param item the item stack to set
     */
    void setItemStack(int slot, ItemStack item);

    /**
     * Atomically swaps the item in the specified slot with the given item.
     *
     * @param slot    the slot index (0-2)
     * @param newItem the new item to place in the slot
     * @return the old item that was previously in the slot
     */
    ItemStack swapItemStack(int slot, ItemStack newItem);
}
