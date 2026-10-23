package org.allaymc.server.container.impl;

import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerType;
import org.allaymc.api.container.interfaces.RecipeContainer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;

/**
 * {@code CraftingContainerImpl} supplies one-item recipe samples for block-oriented
 * crafting containers. Its helper keeps empty slots as the shared air sentinel and
 * copies occupied stacks with their existing network identity, allowing grid
 * implementations to build recipe input without reducing stored counts.
 *
 * @author daoge_cmd
 */
public abstract class CraftingContainerImpl extends BlockContainerImpl implements RecipeContainer {
    /**
     * Creates empty crafting storage using the supplied container layout identity.
     */
    public CraftingContainerImpl(ContainerType<? extends Container> containerType) {
        super(containerType);
    }

    /**
     * Returns a one-item recipe sample for the selected container slot. Empty slots
     * return the shared air sentinel; occupied slots are copied with their existing
     * network ID before the sample count becomes one.
     */
    protected ItemStack pickOne(int slot) {
        if (isEmpty(slot)) {
            return ItemAirStack.AIR_STACK;
        }

        var copy = getItemStack(slot).copy(false);
        copy.setCount(1);
        return copy;
    }
}
