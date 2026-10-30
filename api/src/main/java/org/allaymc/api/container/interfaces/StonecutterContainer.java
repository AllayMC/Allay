package org.allaymc.api.container.interfaces;

import org.allaymc.api.item.ItemStack;

/**
 * {@code StonecutterContainer} exposes named slots for a stonecutter container. Its
 * accessors use the shared container storage and notification rules, so callers can
 * address each slot by its gameplay role without repeating numeric indices. Recipe
 * matching is supplied through {@link RecipeContainer#createRecipeInput()}.
 *
 * @author daoge_cmd
 */
public interface StonecutterContainer extends BlockContainer, RecipeContainer {
    /**
     * The slot constants identify stonecutter storage positions used by the named
     * accessors and container action processing. Stonecutting recipe input slot index.
     */
    int INPUT_SLOT = 0;

    /**
     * Stonecutting recipe result slot index.
     */
    int RESULT_SLOT = 1;

    /**
     * Returns the stored stack in the stonecutting recipe input slot. The result is
     * shared with this container; callers that change its contents must notify the slot
     * afterward.
     */
    default ItemStack getInput() {
        return getItemStack(INPUT_SLOT);
    }

    /**
     * Replaces the stonecutting recipe input slot and notifies its viewers and
     * listeners. {@code itemStack} must be non-{@code null}; the container retains the
     * supplied stack rather than copying it.
     */
    default void setInput(ItemStack itemStack) {
        setItemStack(INPUT_SLOT, itemStack);
    }

    /**
     * Returns the stored stack in the stonecutting recipe result slot. The result is
     * shared with this container; callers that change its contents must notify the slot
     * afterward.
     */
    default ItemStack getResult() {
        return getItemStack(RESULT_SLOT);
    }

    /**
     * Replaces the stonecutting recipe result slot and notifies its viewers and
     * listeners. {@code itemStack} must be non-{@code null}; the container retains the
     * supplied stack rather than copying it.
     */
    default void setResult(ItemStack itemStack) {
        setItemStack(RESULT_SLOT, itemStack);
    }
}
