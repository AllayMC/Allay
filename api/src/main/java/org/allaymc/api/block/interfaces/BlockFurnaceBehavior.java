package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityFurnace;

/**
 * {@code BlockFurnaceBehavior} exposes furnace block behavior together with access
 * to its typed block entity. {@code getBlockEntity} resolves the
 * {@code BlockEntityFurnace} at a world position, while the inherited
 * {@code BlockBehavior} contract supplies the ordinary placement, update, and
 * interaction entry points.
 */
public interface BlockFurnaceBehavior extends BlockBehavior, BlockBlockEntityHolderComponent<BlockEntityFurnace> {
}
