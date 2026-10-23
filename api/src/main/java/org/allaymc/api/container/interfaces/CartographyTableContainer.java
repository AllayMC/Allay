package org.allaymc.api.container.interfaces;

import org.allaymc.api.item.ItemStack;

/**
 * {@code CartographyTableContainer} exposes named slots for a cartography table
 * container. Its accessors use the shared container storage and notification rules, so
 * callers can address each slot by its gameplay role without repeating numeric indices.
 *
 * @author daoge_cmd
 */
public interface CartographyTableContainer extends BlockContainer {
    /**
     * The slot constants identify cartography table storage positions used by the named
     * accessors and container action processing. Map input slot index.
     */
    int INPUT_SLOT = 0;

    /**
     * Additional map ingredient slot index.
     */
    int ADDITIONAL_SLOT = 1;

    /**
     * Cartography result slot index.
     */
    int RESULT_SLOT = 2;

    /**
     * Returns the stored stack in the map input slot. The result is shared with this
     * container; callers that change its contents must notify the slot afterward.
     */
    default ItemStack getInput() {
        return getItemStack(INPUT_SLOT);
    }

    /**
     * Replaces the map input slot and notifies its viewers and listeners. {@code
     * itemStack} must be non-{@code null}; the container retains the supplied stack
     * rather than copying it.
     */
    default void setInput(ItemStack itemStack) {
        setItemStack(INPUT_SLOT, itemStack);
    }

    /**
     * Returns the stored stack in the additional map ingredient slot. The result is
     * shared with this container; callers that change its contents must notify the slot
     * afterward.
     */
    default ItemStack getAdditional() {
        return getItemStack(ADDITIONAL_SLOT);
    }

    /**
     * Replaces the additional map ingredient slot and notifies its viewers and
     * listeners. {@code itemStack} must be non-{@code null}; the container retains the
     * supplied stack rather than copying it.
     */
    default void setAdditional(ItemStack itemStack) {
        setItemStack(ADDITIONAL_SLOT, itemStack);
    }

    /**
     * Returns the stored stack in the cartography result slot. The result is shared with
     * this container; callers that change its contents must notify the slot afterward.
     */
    default ItemStack getResult() {
        return getItemStack(RESULT_SLOT);
    }

    /**
     * Replaces the cartography result slot and notifies its viewers and listeners.
     * {@code itemStack} must be non-{@code null}; the container retains the supplied
     * stack rather than copying it.
     */
    default void setResult(ItemStack itemStack) {
        setItemStack(RESULT_SLOT, itemStack);
    }
}
