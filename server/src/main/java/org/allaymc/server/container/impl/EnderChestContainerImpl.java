package org.allaymc.server.container.impl;

import org.allaymc.api.blockentity.component.BlockEntityEnderChestBaseComponent;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.ContainerViewer;

/**
 * @author IWareQ
 */
public class EnderChestContainerImpl extends BlockContainerImpl {
    public EnderChestContainerImpl() {
        super(ContainerTypes.ENDER_CHEST);
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
