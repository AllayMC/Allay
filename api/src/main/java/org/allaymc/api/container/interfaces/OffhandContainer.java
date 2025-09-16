package org.allaymc.api.container.interfaces;

import org.allaymc.api.container.Container;
import org.allaymc.api.item.ItemStack;

/**
 * OffhandContainer represents a container that holds an offhand item.
 *
 * @author daoge_cmd
 */
public interface OffhandContainer extends Container {
    /**
     * The constant representing the offhand slot index in the container.
     */
    int OFFHAND_SLOT = 0;

    /**
     * Gets the item in the offhand slot.
     *
     * @return the ItemStack in the offhand slot
     */
    default ItemStack getOffhand() {
        return getItemStack(OFFHAND_SLOT);
    }

    /**
     * Sets the item in the offhand slot.
     *
     * @param itemStack the ItemStack to set in the offhand slot
     */
    default void setOffhand(ItemStack itemStack) {
        setItemStack(OFFHAND_SLOT, itemStack);
    }
}
