package org.allaymc.api.container.interfaces;

import org.allaymc.api.container.Container;
import org.allaymc.api.item.ItemStack;

/**
 * {@code ArmorContainer} exposes named slots for an armor equipment container. Its
 * accessors use the shared container storage and notification rules, so callers can
 * address each slot by its gameplay role without repeating numeric indices.
 *
 * @author daoge_cmd
 */
public interface ArmorContainer extends Container {
    /**
     * The slot constants identify armor equipment storage positions used by the named
     * accessors and container action processing. Helmet equipment slot index.
     */
    int HELMET_SLOT = 0;

    /**
     * Chestplate equipment slot index.
     */
    int CHESTPLATE_SLOT = 1;

    /**
     * Leggings equipment slot index.
     */
    int LEGGINGS_SLOT = 2;

    /**
     * Boots equipment slot index.
     */
    int BOOTS_SLOT = 3;

    /**
     * Returns the stored stack in the helmet equipment slot. The result is shared with
     * this container; callers that change its contents must notify the slot afterward.
     */
    default ItemStack getHelmet() {
        return getItemStack(HELMET_SLOT);
    }

    /**
     * Replaces the helmet equipment slot and notifies its viewers and listeners. {@code
     * itemStack} must be non-{@code null}; the container retains the supplied stack
     * rather than copying it.
     */
    default void setHelmet(ItemStack itemStack) {
        setItemStack(HELMET_SLOT, itemStack);
    }

    /**
     * Returns the stored stack in the chestplate equipment slot. The result is shared
     * with this container; callers that change its contents must notify the slot
     * afterward.
     */
    default ItemStack getChestplate() {
        return getItemStack(CHESTPLATE_SLOT);
    }

    /**
     * Replaces the chestplate equipment slot and notifies its viewers and listeners.
     * {@code itemStack} must be non-{@code null}; the container retains the supplied
     * stack rather than copying it.
     */
    default void setChestplate(ItemStack itemStack) {
        setItemStack(CHESTPLATE_SLOT, itemStack);
    }

    /**
     * Returns the stored stack in the leggings equipment slot. The result is shared with
     * this container; callers that change its contents must notify the slot afterward.
     */
    default ItemStack getLeggings() {
        return getItemStack(LEGGINGS_SLOT);
    }

    /**
     * Replaces the leggings equipment slot and notifies its viewers and listeners.
     * {@code itemStack} must be non-{@code null}; the container retains the supplied
     * stack rather than copying it.
     */
    default void setLeggings(ItemStack itemStack) {
        setItemStack(LEGGINGS_SLOT, itemStack);
    }

    /**
     * Returns the stored stack in the boots equipment slot. The result is shared with
     * this container; callers that change its contents must notify the slot afterward.
     */
    default ItemStack getBoots() {
        return getItemStack(BOOTS_SLOT);
    }

    /**
     * Replaces the boots equipment slot and notifies its viewers and listeners. {@code
     * itemStack} must be non-{@code null}; the container retains the supplied stack
     * rather than copying it.
     */
    default void setBoots(ItemStack itemStack) {
        setItemStack(BOOTS_SLOT, itemStack);
    }
}
