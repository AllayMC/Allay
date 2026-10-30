package org.allaymc.api.container.interfaces;

import org.allaymc.api.container.Container;
import org.allaymc.api.item.ItemStack;

/**
 * {@code InventoryContainer} exposes the item occupying the selected player inventory
 * slot. The hand accessors read and replace container storage, while ordinary slot
 * notifications allow implementations to update equipment shown to entity viewers.
 * {@code getHandSlot()} identifies which inventory slot the hand accessors target.
 *
 * @author daoge_cmd
 */
public interface InventoryContainer extends Container {
    /**
     * Returns the stack occupying the selected player inventory slot. The stack remains
     * shared with the container, so direct changes require the corresponding slot
     * notification to update viewers.
     */
    ItemStack getItemInHand();

    /**
     * Replaces the stack occupying the selected player inventory slot and notifies
     * container observers. The supplied stack must be non-{@code null} and remains
     * shared with the container after assignment.
     */
    void setItemInHand(ItemStack itemStack);

    /**
     * Clears the selected player inventory slot by installing the shared air stack. The
     * ordinary slot notification path also updates container viewers and any registered
     * equipment listeners.
     */
    void clearItemInHand();

    /**
     * Returns the inventory index currently used by the hand accessors.
     */
    int getHandSlot();

    /**
     * Selects the inventory slot targeted by subsequent hand accessors. This container
     * setter stores the index without validating the hotbar range or publishing a
     * held-item update to viewers.
     */
    void setHandSlot(int handSlot);
}
