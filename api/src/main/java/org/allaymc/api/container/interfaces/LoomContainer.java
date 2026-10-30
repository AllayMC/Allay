package org.allaymc.api.container.interfaces;

import org.allaymc.api.item.ItemStack;

/**
 * {@code LoomContainer} exposes named slots for a loom container. Its accessors use the
 * shared container storage and notification rules, so callers can address each slot by
 * its gameplay role without repeating numeric indices.
 *
 * @author daoge_cmd
 */
public interface LoomContainer extends BlockContainer {
    /**
     * The slot constants identify loom storage positions used by the named accessors and
     * container action processing. Banner pattern input slot index.
     */
    int INPUT_SLOT = 0;

    /**
     * Banner dye ingredient slot index.
     */
    int DYE_SLOT = 1;

    /**
     * Banner pattern material slot index.
     */
    int MATERIAL_SLOT = 2;

    /**
     * Patterned banner result slot index.
     */
    int RESULT_SLOT = 3;

    /**
     * Returns the stored stack in the banner pattern input slot. The result is shared
     * with this container; callers that change its contents must notify the slot
     * afterward.
     */
    default ItemStack getInput() {
        return getItemStack(INPUT_SLOT);
    }

    /**
     * Replaces the banner pattern input slot and notifies its viewers and listeners.
     * {@code itemStack} must be non-{@code null}; the container retains the supplied
     * stack rather than copying it.
     */
    default void setInput(ItemStack itemStack) {
        setItemStack(INPUT_SLOT, itemStack);
    }

    /**
     * Returns the stored stack in the banner dye ingredient slot. The result is shared
     * with this container; callers that change its contents must notify the slot
     * afterward.
     */
    default ItemStack getDye() {
        return getItemStack(DYE_SLOT);
    }

    /**
     * Replaces the banner dye ingredient slot and notifies its viewers and listeners.
     * {@code itemStack} must be non-{@code null}; the container retains the supplied
     * stack rather than copying it.
     */
    default void setDye(ItemStack itemStack) {
        setItemStack(DYE_SLOT, itemStack);
    }

    /**
     * Returns the stored stack in the banner pattern material slot. The result is shared
     * with this container; callers that change its contents must notify the slot
     * afterward.
     */
    default ItemStack getMaterial() {
        return getItemStack(MATERIAL_SLOT);
    }

    /**
     * Replaces the banner pattern material slot and notifies its viewers and listeners.
     * {@code itemStack} must be non-{@code null}; the container retains the supplied
     * stack rather than copying it.
     */
    default void setMaterial(ItemStack itemStack) {
        setItemStack(MATERIAL_SLOT, itemStack);
    }

    /**
     * Returns the stored stack in the patterned banner result slot. The result is shared
     * with this container; callers that change its contents must notify the slot
     * afterward.
     */
    default ItemStack getResult() {
        return getItemStack(RESULT_SLOT);
    }

    /**
     * Replaces the patterned banner result slot and notifies its viewers and listeners.
     * {@code itemStack} must be non-{@code null}; the container retains the supplied
     * stack rather than copying it.
     */
    default void setResult(ItemStack itemStack) {
        setItemStack(RESULT_SLOT, itemStack);
    }
}
