package org.allaymc.api.container.impl;

import org.allaymc.api.container.Container;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.container.RecipeContainer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;

/**
 * @author daoge_cmd
 */
public abstract class CraftingContainer extends BlockContainer implements RecipeContainer {
    public CraftingContainer(FullContainerType<? extends Container> containerType) {
        super(containerType);
    }

    protected ItemStack pickOne(int slot) {
        if (isEmpty(slot)) return ItemAirStack.AIR_STACK;
        var copy = getItemStack(slot).copy(false);
        copy.setCount(1);
        return copy;
    }

    public abstract int calculateShouldConsumedItemSlotCount();
}
