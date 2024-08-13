package org.allaymc.api.container.impl;

import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.item.ItemStack;

/**
 * Allay Project 2024/8/11
 *
 * @author daoge_cmd
 */
public class FurnaceContainer extends BlockContainer {
    public static int INGREDIENT_SLOT = 0;
    public static int FUEL_SLOT = 1;
    public static int RESULT_SLOT = 2;

    public FurnaceContainer() {
        super(FullContainerType.FURNACE);
    }

    public FurnaceContainer(FullContainerType<?> containerType) {
        super(containerType);
    }

    public ItemStack getIngredient() {
        return getItemStack(INGREDIENT_SLOT);
    }

    public void setIngredient(ItemStack itemStack) {
        setItemStack(INGREDIENT_SLOT, itemStack);
    }

    public ItemStack getFuel() {
        return getItemStack(FUEL_SLOT);
    }

    public void setFuel(ItemStack itemStack) {
        setItemStack(FUEL_SLOT, itemStack);
    }

    public ItemStack getResult() {
        return getItemStack(RESULT_SLOT);
    }

    public void setResult(ItemStack itemStack) {
        setItemStack(RESULT_SLOT, itemStack);
    }
}
