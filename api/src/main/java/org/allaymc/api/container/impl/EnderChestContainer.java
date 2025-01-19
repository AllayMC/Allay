package org.allaymc.api.container.impl;

import org.allaymc.api.blockentity.component.BlockEntityEnderChestBaseComponent;
import org.allaymc.api.container.ContainerViewer;
import org.allaymc.api.container.FullContainerType;

/**
 * @author IWareQ
 */
public class EnderChestContainer extends BlockContainer {
    public EnderChestContainer() {
        super(FullContainerType.ENDER_CHEST);
    }

    @Override
    public void onOpen(ContainerViewer viewer) {
        super.onOpen(viewer);

        if (blockPos.dimension().getBlockEntity(blockPos) instanceof BlockEntityEnderChestBaseComponent enderChest) {
            enderChest.setViewersCount(enderChest.getViewersCount() + 1);
        }
    }

    @Override
    public void onClose(ContainerViewer viewer) {
        super.onClose(viewer);

        if (blockPos.dimension().getBlockEntity(blockPos) instanceof BlockEntityEnderChestBaseComponent enderChest) {
            enderChest.setViewersCount(enderChest.getViewersCount() - 1);
        }
    }
}
