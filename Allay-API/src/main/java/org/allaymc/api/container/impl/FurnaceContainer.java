package org.allaymc.api.container.impl;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.container.BaseContainer;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.item.ItemStack;
import org.joml.Vector3ic;

/**
 * Allay Project 2024/8/11
 *
 * @author daoge_cmd
 */
public class FurnaceContainer extends BaseContainer {

    public static int INGREDIENT_SLOT = 0;
    public static int FUEL_SLOT = 1;
    public static int RESULT_SLOT = 2;

    @Getter
    @Setter
    protected Vector3ic blockPos;

    public FurnaceContainer() {
        super(FullContainerType.FURNACE);
    }

    public ItemStack getIngredient() {
        return getItemStack(INGREDIENT_SLOT);
    }

    public ItemStack getFuel() {
        return getItemStack(FUEL_SLOT);
    }

    public ItemStack getResult() {
        return getItemStack(RESULT_SLOT);
    }

    public void setIngredient(ItemStack itemStack) {
        setItemStack(INGREDIENT_SLOT, itemStack);
    }

    public void setFuel(ItemStack itemStack) {
        setItemStack(FUEL_SLOT, itemStack);
    }

    public void setResult(ItemStack itemStack) {
        setItemStack(RESULT_SLOT, itemStack);
    }

    @Override
    public boolean hasBlockPos() {
        return true;
    }
}
