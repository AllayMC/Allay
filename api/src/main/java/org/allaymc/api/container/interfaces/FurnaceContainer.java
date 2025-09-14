package org.allaymc.api.container.interfaces;

import org.allaymc.api.item.ItemStack;

/**
 * @author daoge_cmd
 */
public interface FurnaceContainer extends BlockContainer {
    int INGREDIENT_SLOT = 0;
    int FUEL_SLOT = 1;
    int RESULT_SLOT = 2;

    default ItemStack getIngredient() {
        return getItemStack(INGREDIENT_SLOT);
    }

    default void setIngredient(ItemStack itemStack) {
        setItemStack(INGREDIENT_SLOT, itemStack);
    }

    default ItemStack getFuel() {
        return getItemStack(FUEL_SLOT);
    }

    default void setFuel(ItemStack itemStack) {
        setItemStack(FUEL_SLOT, itemStack);
    }

    default ItemStack getResult() {
        return getItemStack(RESULT_SLOT);
    }

    default void setResult(ItemStack itemStack) {
        setItemStack(RESULT_SLOT, itemStack);
    }
}
