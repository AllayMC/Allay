package org.allaymc.api.blockentity.component;

import org.allaymc.api.block.data.PotSherds;
import org.allaymc.api.item.ItemStack;

/**
 * @author IWareQ
 */
public interface BlockEntityDecoratedPotBaseComponent extends BlockEntityBaseComponent {
    /**
     * Maximum number of items that can be stored in a decorated pot.
     */
    int MAX_STACK_SIZE = 64;

    /**
     * Try to insert one item into the decorated pot.
     *
     * @param item the item to insert (only 1 will be inserted)
     * @return {@code true} if the item was inserted, {@code false} otherwise
     */
    boolean tryInsertItem(ItemStack item);

    /**
     * Get the item stored in the decorated pot.
     *
     * @return the item, or {@code null} if empty
     */
    ItemStack getItem();

    /**
     * Set the item stored in the decorated pot.
     *
     * @param item the item to store, or {@code null} to clear
     */
    void setItem(ItemStack item);

    /**
     * Check if the decorated pot is empty.
     *
     * @return {@code true} if empty, {@code false} otherwise
     */
    default boolean isEmpty() {
        return this.getItem() == null;
    }

    /**
     * Check if the decorated pot is full.
     *
     * @return {@code true} if full, {@code false} otherwise
     */
    default boolean isFull() {
        var item = this.getItem();
        return item != null && item.getCount() >= MAX_STACK_SIZE;
    }

    /**
     * Clear the decorated pot.
     */
    default void clear() {
        this.setItem(null);
    }

    /**
     * Get the comparator output signal based on the fill level.
     *
     * @return comparator signal strength (0-15)
     */
    default int getComparatorOutput() {
        var item = this.getItem();
        if (item == null) {
            return 0;
        }

        return Math.max(1, (int) Math.floor(((double) item.getCount() / MAX_STACK_SIZE) * 14d) + 1);
    }

    /**
     * Get the sherds on this decorated pot.
     *
     * @return the pot sherds
     */
    PotSherds getSherds();

    /**
     * Set the sherds on this decorated pot.
     *
     * @param sherds the pot sherds
     */
    void setSherds(PotSherds sherds);
}
