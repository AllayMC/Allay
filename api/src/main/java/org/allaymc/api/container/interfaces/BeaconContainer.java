package org.allaymc.api.container.interfaces;

import org.allaymc.api.item.ItemStack;

/**
 * BeaconContainer represents a container for a beacon block.
 *
 * @author daoge_cmd
 */
public interface BeaconContainer extends BlockContainer {
    /**
     * The constant representing the payment slot index in the beacon container.
     */
    int BEACON_PAYMENT_SLOT = 0;

    /**
     * Get the item stack in the payment slot.
     *
     * @return The item stack in the payment slot
     */
    default ItemStack getBeaconPayment() {
        return this.getItemStack(BEACON_PAYMENT_SLOT);
    }

    /**
     * Sets the item stack in the payment slot.
     *
     * @param itemStack The item stack to set in the payment slot
     */
    default void setBeaconPayment(ItemStack itemStack) {
        this.setItemStack(BEACON_PAYMENT_SLOT, itemStack);
    }
}
