package org.allaymc.api.container.interfaces;

import org.allaymc.api.item.ItemStack;

/**
 * {@code GrindstoneContainer} exposes named slots for a grindstone container. Its
 * accessors use the shared container storage and notification rules, so callers can
 * address each slot by its gameplay role without repeating numeric indices.
 *
 * @author daoge_cmd
 */
public interface GrindstoneContainer extends BlockContainer {
    /**
     * The slot constants identify grindstone storage positions used by the named
     * accessors and container action processing. First grindstone input slot index.
     */
    int INPUT_SLOT = 0;

    /**
     * Second grindstone input slot index.
     */
    int ADDITIONAL_SLOT = 1;

    /**
     * Grindstone result slot index.
     */
    int RESULT_SLOT = 2;

    /**
     * Returns the stored stack in the first grindstone input slot. The result is shared
     * with this container; callers that change its contents must notify the slot
     * afterward.
     */
    default ItemStack getInput() {
        return getItemStack(INPUT_SLOT);
    }

    /**
     * Replaces the first grindstone input slot and notifies its viewers and listeners.
     * {@code itemStack} must be non-{@code null}; the container retains the supplied
     * stack rather than copying it.
     */
    default void setInput(ItemStack itemStack) {
        setItemStack(INPUT_SLOT, itemStack);
    }

    /**
     * Returns the stored stack in the second grindstone input slot. The result is shared
     * with this container; callers that change its contents must notify the slot
     * afterward.
     */
    default ItemStack getAdditional() {
        return getItemStack(ADDITIONAL_SLOT);
    }

    /**
     * Replaces the second grindstone input slot and notifies its viewers and listeners.
     * {@code itemStack} must be non-{@code null}; the container retains the supplied
     * stack rather than copying it.
     */
    default void setAdditional(ItemStack itemStack) {
        setItemStack(ADDITIONAL_SLOT, itemStack);
    }

    /**
     * Returns the stored stack in the grindstone result slot. The result is shared with
     * this container; callers that change its contents must notify the slot afterward.
     */
    default ItemStack getResult() {
        return getItemStack(RESULT_SLOT);
    }

    /**
     * Replaces the grindstone result slot and notifies its viewers and listeners. {@code
     * itemStack} must be non-{@code null}; the container retains the supplied stack
     * rather than copying it.
     */
    default void setResult(ItemStack itemStack) {
        setItemStack(RESULT_SLOT, itemStack);
    }
}
