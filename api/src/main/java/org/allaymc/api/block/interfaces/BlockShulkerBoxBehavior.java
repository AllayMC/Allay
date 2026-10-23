package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityShulkerBox;

/**
 * {@code BlockShulkerBoxBehavior} exposes shulker box block behavior together with
 * access to its typed block entity. {@code getBlockEntity} resolves the
 * {@code BlockEntityShulkerBox} at a world position, while the inherited
 * {@code BlockBehavior} contract supplies the ordinary placement, update, and
 * interaction entry points.
 */
public interface BlockShulkerBoxBehavior extends BlockBehavior,
        BlockBlockEntityHolderComponent<BlockEntityShulkerBox> {
}
