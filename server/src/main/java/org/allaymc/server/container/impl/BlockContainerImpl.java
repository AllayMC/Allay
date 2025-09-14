package org.allaymc.server.container.impl;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerType;
import org.allaymc.api.container.interfaces.BlockContainer;
import org.allaymc.api.container.interfaces.EnchantTableContainer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.position.Position3ic;

/**
 * @author IWareQ
 */
public class BlockContainerImpl extends BaseContainer implements BlockContainer {
    @Getter
    @Setter
    protected Position3ic blockPos;

    public BlockContainerImpl(ContainerType<? extends Container> containerType) {
        super(containerType);
    }

    public ItemStack getInput() {
        return getItemStack(EnchantTableContainer.INPUT_SLOT);
    }

    public ItemStack getMaterial() {
        return getItemStack(EnchantTableContainer.MATERIAL_SLOT);
    }
}
