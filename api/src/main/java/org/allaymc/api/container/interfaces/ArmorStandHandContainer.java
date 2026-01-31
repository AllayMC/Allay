package org.allaymc.api.container.interfaces;

import org.allaymc.api.container.Container;
import org.allaymc.api.item.ItemStack;

/**
 * Container for armor stand's main hand item.
 *
 * @author daoge_cmd
 */
public interface ArmorStandHandContainer extends Container {

    /**
     * Gets the item in hand.
     *
     * @return the item in hand
     */
    ItemStack getItemInHand();

    /**
     * Sets the item in hand.
     *
     * @param itemStack the item to set
     */
    void setItemInHand(ItemStack itemStack);

    /**
     * Clears the item in hand.
     */
    void clearItemInHand();
}
