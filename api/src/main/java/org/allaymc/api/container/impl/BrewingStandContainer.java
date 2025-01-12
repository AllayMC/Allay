package org.allaymc.api.container.impl;

import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.item.ItemStack;
import org.jetbrains.annotations.Range;

/**
 * @author IWareQ
 */
public class BrewingStandContainer extends BlockContainer {
    public static final int REAGENT_SLOT = 0;
    public static final int FUEL_SLOT = 4;

    public BrewingStandContainer() {
        super(FullContainerType.BREWING_STAND);
    }

    public ItemStack getReagent() {
        return getItemStack(REAGENT_SLOT);
    }

    public void setReagent(ItemStack itemStack) {
        setItemStack(REAGENT_SLOT, itemStack);
    }

    public ItemStack getFuel() {
        return getItemStack(FUEL_SLOT);
    }

    public void setFuel(ItemStack itemStack) {
        setItemStack(FUEL_SLOT, itemStack);
    }

    public ItemStack getResult(@Range(from = 0, to = 2) int slot) {
        return getItemStack(slot + 1);
    }

    public void setResult(@Range(from = 0, to = 2) int slot, ItemStack itemStack) {
        setItemStack(slot + 1, itemStack);
    }
}
