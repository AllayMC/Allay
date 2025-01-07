package org.allaymc.api.container.impl;

import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.item.ItemStack;

/**
 * @author daoge_cmd
 */
public class BeaconContainer extends BlockContainer {
    public static final int BEACON_PAYMENT_SLOT = 0;

    public BeaconContainer() {
        super(FullContainerType.BEACON);
    }

    /**
     * Get the item stack in the payment slot.
     *
     * @return The item stack in the payment slot.
     */
    public ItemStack getBeaconPayment() {
        return this.getItemStack(BEACON_PAYMENT_SLOT);
    }
}
