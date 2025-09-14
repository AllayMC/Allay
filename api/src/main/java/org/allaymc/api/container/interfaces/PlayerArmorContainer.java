package org.allaymc.api.container.interfaces;

import org.allaymc.api.container.Container;
import org.allaymc.api.item.ItemStack;

/**
 * @author daoge_cmd
 */
public interface PlayerArmorContainer extends Container {
    int HELMET_SLOT = 0;
    int CHESTPLATE_SLOT = 1;
    int LEGGINGS_SLOT = 2;
    int BOOTS_SLOT = 3;
    float KNOCKBACK_RESISTANCE_PER_NETHERITE_ARMOR = 0.1f;

    void onArmorChange(ItemStack newItemStack);

    default ItemStack getHelmet() {
        return getItemStack(HELMET_SLOT);
    }

    default void setHelmet(ItemStack itemStack) {
        setItemStack(HELMET_SLOT, itemStack);
    }

    default ItemStack getChestplate() {
        return getItemStack(CHESTPLATE_SLOT);
    }

    default void setChestplate(ItemStack itemStack) {
        setItemStack(CHESTPLATE_SLOT, itemStack);
    }

    default ItemStack getLeggings() {
        return getItemStack(LEGGINGS_SLOT);
    }

    default void setLeggings(ItemStack itemStack) {
        setItemStack(LEGGINGS_SLOT, itemStack);
    }

    default ItemStack getBoots() {
        return getItemStack(BOOTS_SLOT);
    }

    default void setBoots(ItemStack itemStack) {
        setItemStack(BOOTS_SLOT, itemStack);
    }
}
