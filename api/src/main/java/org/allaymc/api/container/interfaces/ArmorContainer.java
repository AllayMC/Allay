package org.allaymc.api.container.interfaces;

import org.allaymc.api.container.Container;
import org.allaymc.api.item.ItemStack;

/**
 * ArmorContainer represents a container that holds armor items.
 *
 * @author daoge_cmd
 */
public interface ArmorContainer extends Container {
    /**
     * The constant representing the helmet slot index in the armor container.
     */
    int HELMET_SLOT = 0;

    /**
     * The constant representing the chestplate slot index in the armor container.
     */
    int CHESTPLATE_SLOT = 1;

    /**
     * The constant representing the leggings slot index in the armor container.
     */
    int LEGGINGS_SLOT = 2;

    /**
     * The constant representing the boots slot index in the armor container.
     */
    int BOOTS_SLOT = 3;

    /**
     * Gets the item in the helmet slot.
     *
     * @return the ItemStack in the helmet slot
     */
    default ItemStack getHelmet() {
        return getItemStack(HELMET_SLOT);
    }

    /**
     * Sets the item in the helmet slot.
     *
     * @param itemStack the ItemStack to set in the helmet slot
     */
    default void setHelmet(ItemStack itemStack) {
        setItemStack(HELMET_SLOT, itemStack);
    }

    /**
     * Gets the item in the chestplate slot.
     *
     * @return the ItemStack in the chestplate slot
     */
    default ItemStack getChestplate() {
        return getItemStack(CHESTPLATE_SLOT);
    }

    /**
     * Sets the item in the chestplate slot.
     *
     * @param itemStack the ItemStack to set in the chestplate slot
     */
    default void setChestplate(ItemStack itemStack) {
        setItemStack(CHESTPLATE_SLOT, itemStack);
    }

    /**
     * Gets the item in the leggings slot.
     *
     * @return the ItemStack in the leggings slot
     */
    default ItemStack getLeggings() {
        return getItemStack(LEGGINGS_SLOT);
    }

    /**
     * Sets the item in the leggings slot.
     *
     * @param itemStack the ItemStack to set in the leggings slot
     */
    default void setLeggings(ItemStack itemStack) {
        setItemStack(LEGGINGS_SLOT, itemStack);
    }

    /**
     * Gets the item in the boots slot.
     *
     * @return the ItemStack in the boots slot
     */
    default ItemStack getBoots() {
        return getItemStack(BOOTS_SLOT);
    }

    /**
     * Sets the item in the boots slot.
     *
     * @param itemStack the ItemStack to set in the boots slot
     */
    default void setBoots(ItemStack itemStack) {
        setItemStack(BOOTS_SLOT, itemStack);
    }
}
