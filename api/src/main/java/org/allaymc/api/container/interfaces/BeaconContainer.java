package org.allaymc.api.container.interfaces;

import org.allaymc.api.item.ItemStack;

/**
 * @author daoge_cmd
 */
public interface BeaconContainer extends BlockContainer {
    int BEACON_PAYMENT_SLOT = 0;

    /**
     * Get the item stack in the payment slot.
     *
     * @return The item stack in the payment slot
     */
    default ItemStack getBeaconPayment() {
        return this.getItemStack(BEACON_PAYMENT_SLOT);
    }
}
