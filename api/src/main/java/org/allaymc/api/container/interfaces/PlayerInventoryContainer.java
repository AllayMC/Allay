package org.allaymc.api.container.interfaces;

import org.allaymc.api.container.Container;
import org.allaymc.api.item.ItemStack;

/**
 * PlayerInventoryContainer represents a container that holds a player's inventory items.
 *
 * @author daoge_cmd
 */
public interface PlayerInventoryContainer extends Container {
    /**
     * Gets the item currently in the player's hand.
     *
     * @return the ItemStack in the player's hand
     */
    ItemStack getItemInHand();

    /**
     * Sets the item in the player's hand.
     *
     * @param itemStack the ItemStack to set in the player's hand
     */
    void setItemInHand(ItemStack itemStack);

    /**
     * Clears the item in the player's hand, effectively removing it.
     */
    void clearItemInHand();

    /**
     * Gets the current hand slot index that the player is using.
     *
     * @return the index of the current hand slot
     */
    int getHandSlot();

    /**
     * Sets the hand slot index for the player.
     *
     * @param handSlot the index to set as the current hand slot
     */
    void setHandSlot(int handSlot);
}
