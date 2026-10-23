package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityChest;

/**
 * {@code BlockTrappedChestBehavior} exposes trapped chest block behavior together
 * with access to its typed block entity. {@code getBlockEntity} resolves the
 * {@code BlockEntityChest} at a world position, while the inherited
 * {@code BlockBehavior} contract supplies the ordinary placement, update, and
 * interaction entry points.
 */
public interface BlockTrappedChestBehavior extends
        BlockBehavior,
        BlockBlockEntityHolderComponent<BlockEntityChest> {
}
