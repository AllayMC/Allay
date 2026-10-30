package org.allaymc.api.container.interfaces;

import org.allaymc.api.container.Container;
import org.allaymc.api.item.ItemStack;

/**
 * {@code OffhandContainer} exposes named slots for an offhand equipment container. Its
 * accessors use the shared container storage and notification rules, so callers can
 * address each slot by its gameplay role without repeating numeric indices.
 *
 * @author daoge_cmd
 */
public interface OffhandContainer extends Container {
    /**
     * The slot constants identify offhand equipment storage positions used by the named
     * accessors and container action processing. Offhand equipment item slot index.
     */
    int OFFHAND_SLOT = 0;

    /**
     * Returns the stored stack in the offhand equipment item slot. The result is shared
     * with this container; callers that change its contents must notify the slot
     * afterward.
     */
    default ItemStack getOffhand() {
        return getItemStack(OFFHAND_SLOT);
    }

    /**
     * Replaces the offhand equipment item slot and notifies its viewers and listeners.
     * {@code itemStack} must be non-{@code null}; the container retains the supplied
     * stack rather than copying it.
     */
    default void setOffhand(ItemStack itemStack) {
        setItemStack(OFFHAND_SLOT, itemStack);
    }
}
