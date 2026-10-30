package org.allaymc.api.container.interfaces;

import org.allaymc.api.item.ItemStack;

/**
 * {@code SmithingTableContainer} exposes named slots for a smithing table container. Its
 * accessors use the shared container storage and notification rules, so callers can
 * address each slot by its gameplay role without repeating numeric indices. Recipe
 * matching is supplied through {@link RecipeContainer#createRecipeInput()}.
 *
 * @author daoge_cmd
 */
public interface SmithingTableContainer extends BlockContainer, RecipeContainer {
    /**
     * The slot constants identify smithing table storage positions used by the named
     * accessors and container action processing. Smithing equipment input slot index.
     */
    int INPUT_SLOT = 0;

    /**
     * Smithing material input slot index.
     */
    int MATERIAL_SLOT = 1;

    /**
     * Smithing template input slot index.
     */
    int TEMPLATE_SLOT = 2;

    /**
     * Smithing recipe result slot index.
     */
    int RESULT_SLOT = 3;

    /**
     * Returns the stored stack in the smithing equipment input slot. The result is
     * shared with this container; callers that change its contents must notify the slot
     * afterward.
     */
    default ItemStack getInput() {
        return getItemStack(INPUT_SLOT);
    }

    /**
     * Replaces the smithing equipment input slot and notifies its viewers and listeners.
     * {@code itemStack} must be non-{@code null}; the container retains the supplied
     * stack rather than copying it.
     */
    default void setInput(ItemStack itemStack) {
        setItemStack(INPUT_SLOT, itemStack);
    }

    /**
     * Returns the stored stack in the smithing material input slot. The result is shared
     * with this container; callers that change its contents must notify the slot
     * afterward.
     */
    default ItemStack getMaterial() {
        return getItemStack(MATERIAL_SLOT);
    }

    /**
     * Replaces the smithing material input slot and notifies its viewers and listeners.
     * {@code itemStack} must be non-{@code null}; the container retains the supplied
     * stack rather than copying it.
     */
    default void setMaterial(ItemStack itemStack) {
        setItemStack(MATERIAL_SLOT, itemStack);
    }

    /**
     * Returns the stored stack in the smithing template input slot. The result is shared
     * with this container; callers that change its contents must notify the slot
     * afterward.
     */
    default ItemStack getTemplate() {
        return getItemStack(TEMPLATE_SLOT);
    }

    /**
     * Replaces the smithing template input slot and notifies its viewers and listeners.
     * {@code itemStack} must be non-{@code null}; the container retains the supplied
     * stack rather than copying it.
     */
    default void setTemplate(ItemStack itemStack) {
        setItemStack(TEMPLATE_SLOT, itemStack);
    }

    /**
     * Returns the stored stack in the smithing recipe result slot. The result is shared
     * with this container; callers that change its contents must notify the slot
     * afterward.
     */
    default ItemStack getResult() {
        return getItemStack(RESULT_SLOT);
    }

    /**
     * Replaces the smithing recipe result slot and notifies its viewers and listeners.
     * {@code itemStack} must be non-{@code null}; the container retains the supplied
     * stack rather than copying it.
     */
    default void setResult(ItemStack itemStack) {
        setItemStack(RESULT_SLOT, itemStack);
    }
}
