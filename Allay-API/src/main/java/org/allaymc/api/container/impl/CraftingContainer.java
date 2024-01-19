package org.allaymc.api.container.impl;

import org.allaymc.api.container.BaseContainer;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.recipe.input.CraftingInput;

/**
 * Allay Project 2024/1/19
 *
 * @author daoge_cmd
 */
public abstract class CraftingContainer extends BaseContainer {
    public CraftingContainer(FullContainerType<? extends Container> containerType) {
        super(containerType);
    }

    protected ItemStack pickOne(int slot) {
        if (isEmpty(slot)) return Container.EMPTY_SLOT_PLACE_HOLDER;
        var copy = getItemStack(slot).copy(false);
        copy.setCount(1);
        return copy;
    }

    public abstract CraftingInput createCraftingInput();

    public abstract int calculateShouldConsumedItemCount();
}
