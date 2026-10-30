package org.allaymc.api.container.interfaces;

import org.allaymc.api.item.ItemStack;

/**
 * {@code AnvilContainer} exposes named slots for an anvil container. Its accessors use
 * the shared container storage and notification rules, so callers can address each slot
 * by its gameplay role without repeating numeric indices.
 *
 * @author daoge_cmd
 */
public interface AnvilContainer extends BlockContainer {
    /**
     * The slot constants identify anvil storage positions used by the named accessors
     * and container action processing. Repair or rename input slot index.
     */
    int INPUT_SLOT = 0;

    /**
     * Repair material or second input slot index.
     */
    int MATERIAL_SLOT = 1;

    /**
     * Anvil result slot index.
     */
    int RESULT_SLOT = 2;

    /**
     * Returns the stored stack in the repair or rename input slot. The result is shared
     * with this container; callers that change its contents must notify the slot
     * afterward.
     */
    default ItemStack getInput() {
        return getItemStack(INPUT_SLOT);
    }

    /**
     * Replaces the repair or rename input slot and notifies its viewers and listeners.
     * {@code itemStack} must be non-{@code null}; the container retains the supplied
     * stack rather than copying it.
     */
    default void setInput(ItemStack itemStack) {
        setItemStack(INPUT_SLOT, itemStack);
    }

    /**
     * Returns the stored stack in the repair material or second input slot. The result
     * is shared with this container; callers that change its contents must notify the
     * slot afterward.
     */
    default ItemStack getMaterial() {
        return getItemStack(MATERIAL_SLOT);
    }

    /**
     * Replaces the repair material or second input slot and notifies its viewers and
     * listeners. {@code itemStack} must be non-{@code null}; the container retains the
     * supplied stack rather than copying it.
     */
    default void setMaterial(ItemStack itemStack) {
        setItemStack(MATERIAL_SLOT, itemStack);
    }

    /**
     * Returns the stored stack in the anvil result slot. The result is shared with this
     * container; callers that change its contents must notify the slot afterward.
     */
    default ItemStack getResult() {
        return getItemStack(RESULT_SLOT);
    }

    /**
     * Replaces the anvil result slot and notifies its viewers and listeners. {@code
     * itemStack} must be non-{@code null}; the container retains the supplied stack
     * rather than copying it.
     */
    default void setResult(ItemStack itemStack) {
        setItemStack(RESULT_SLOT, itemStack);
    }
}
