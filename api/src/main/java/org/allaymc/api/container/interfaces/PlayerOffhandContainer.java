package org.allaymc.api.container.interfaces;

import org.allaymc.api.container.Container;
import org.allaymc.api.item.ItemStack;

/**
 * PlayerOffhandContainer represents a container that holds a player's offhand item.
 *
 * @author daoge_cmd
 */
public interface PlayerOffhandContainer extends Container {
    /**
     * The constant representing the offhand slot index in the container.
     */
    int OFFHAND_SLOT = 0;

    /**
     * Gets the item in the player's offhand slot.
     *
     * @return the ItemStack in the offhand slot
     */
    default ItemStack getOffhand() {
        return getItemStack(OFFHAND_SLOT);
    }

    /**
     * Sets the item in the player's offhand slot.
     *
     * @param itemStack the ItemStack to set in the offhand slot
     */
    default void setOffhand(ItemStack itemStack) {
        setItemStack(OFFHAND_SLOT, itemStack);
    }
}
