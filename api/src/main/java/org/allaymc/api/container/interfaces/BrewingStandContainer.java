package org.allaymc.api.container.interfaces;

import org.allaymc.api.item.ItemStack;
import org.jetbrains.annotations.Range;

/**
 * @author daoge_cmd
 */
public interface BrewingStandContainer extends BlockContainer {
    int REAGENT_SLOT = 0;
    int FUEL_SLOT = 4;

    default ItemStack getReagent() {
        return getItemStack(REAGENT_SLOT);
    }

    default void setReagent(ItemStack itemStack) {
        setItemStack(REAGENT_SLOT, itemStack);
    }

    default ItemStack getFuel() {
        return getItemStack(FUEL_SLOT);
    }

    default void setFuel(ItemStack itemStack) {
        setItemStack(FUEL_SLOT, itemStack);
    }

    default ItemStack getResult(@Range(from = 0, to = 2) int slot) {
        return getItemStack(slot + 1);
    }

    default void setResult(@Range(from = 0, to = 2) int slot, ItemStack itemStack) {
        setItemStack(slot + 1, itemStack);
    }
}
