package org.allaymc.api.container.impl;

import org.allaymc.api.container.BaseContainer;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.item.ItemStack;

/**
 * Allay Project 2023/7/22
 *
 * @author daoge_cmd
 */
public class PlayerArmorContainer extends BaseContainer {

    public static final int HELMET_SLOT = 0;
    public static final int CHESTPLATE_SLOT = 1;
    public static final int LEGGINGS_SLOT = 2;
    public static final int BOOTS_SLOT = 3;

    public PlayerArmorContainer() {
        super(FullContainerType.ARMOR);
    }

    public ItemStack getHelmet() {
        return getItemStack(HELMET_SLOT);
    }

    public ItemStack getChestplate() {
        return getItemStack(CHESTPLATE_SLOT);
    }

    public ItemStack getLeggings() {
        return getItemStack(LEGGINGS_SLOT);
    }

    public ItemStack getBoots() {
        return getItemStack(BOOTS_SLOT);
    }

    public void setHelmet(ItemStack itemStack) {
        setItemStack(HELMET_SLOT, itemStack);
    }

    public void setChestplate(ItemStack itemStack) {
        setItemStack(CHESTPLATE_SLOT, itemStack);
    }

    public void setLeggings(ItemStack itemStack) {
        setItemStack(LEGGINGS_SLOT, itemStack);
    }

    public void setBoots(ItemStack itemStack) {
        setItemStack(BOOTS_SLOT, itemStack);
    }
}
