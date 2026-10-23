package org.allaymc.api.container.interfaces;

import org.allaymc.api.item.ItemStack;

/**
 * {@code BeaconContainer} exposes named slots for a beacon container. Its accessors use
 * the shared container storage and notification rules, so callers can address each slot
 * by its gameplay role without repeating numeric indices.
 *
 * @author daoge_cmd
 */
public interface BeaconContainer extends BlockContainer {
    /**
     * The slot constants identify beacon storage positions used by the named accessors
     * and container action processing. Beacon effect payment slot index.
     */
    int BEACON_PAYMENT_SLOT = 0;

    /**
     * Returns the stored stack in the beacon effect payment slot. The result is shared
     * with this container; callers that change its contents must notify the slot
     * afterward.
     */
    default ItemStack getBeaconPayment() {
        return this.getItemStack(BEACON_PAYMENT_SLOT);
    }

    /**
     * Replaces the beacon effect payment slot and notifies its viewers and listeners.
     * {@code itemStack} must be non-{@code null}; the container retains the supplied
     * stack rather than copying it.
     */
    default void setBeaconPayment(ItemStack itemStack) {
        this.setItemStack(BEACON_PAYMENT_SLOT, itemStack);
    }
}
