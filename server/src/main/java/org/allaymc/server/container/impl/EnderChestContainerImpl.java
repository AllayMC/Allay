package org.allaymc.server.container.impl;

import org.allaymc.api.blockentity.component.BlockEntityEnderChestBaseComponent;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.ContainerViewer;

/**
 * {@code EnderChestContainerImpl} stores player-owned ender chest items and links window
 * lifetime to the ender chest block at its current position. After ordinary open or
 * close listeners run, the matching block entity's viewer count changes to drive its
 * block-side viewing state.
 *
 * @author IWareQ
 */
public class EnderChestContainerImpl extends BlockContainerImpl {
    /**
     * Creates empty twenty-seven-slot storage using the ender chest container identity.
     */
    public EnderChestContainerImpl() {
        super(ContainerTypes.ENDER_CHEST);
    }

    /**
     * Runs open listeners and then increments the ender chest block entity's viewer
     * count. {@code blockPos} must be initialized; a different block entity at that
     * location receives no count adjustment.
     */
    @Override
    public void onOpen(ContainerViewer viewer) {
        super.onOpen(viewer);

        if (blockPos.dimension().getBlockEntity(blockPos) instanceof BlockEntityEnderChestBaseComponent enderChest) {
            enderChest.setViewersCount(enderChest.getViewersCount() + 1);
        }
    }

    /**
     * Runs close listeners and then decrements the ender chest block entity's viewer
     * count. {@code blockPos} must still identify the relevant block; other block entity
     * types receive no count adjustment.
     */
    @Override
    public void onClose(ContainerViewer viewer) {
        super.onClose(viewer);

        if (blockPos.dimension().getBlockEntity(blockPos) instanceof BlockEntityEnderChestBaseComponent enderChest) {
            enderChest.setViewersCount(enderChest.getViewersCount() - 1);
        }
    }
}
