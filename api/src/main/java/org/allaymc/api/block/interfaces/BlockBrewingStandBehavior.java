package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityBrewingStand;

/**
 * {@code BlockBrewingStandBehavior} exposes brewing stand block behavior together
 * with access to its typed block entity. {@code getBlockEntity} resolves the
 * {@code BlockEntityBrewingStand} at a world position, while the inherited
 * {@code BlockBehavior} contract supplies the ordinary placement, update, and
 * interaction entry points.
 */
public interface BlockBrewingStandBehavior extends BlockBehavior, BlockBlockEntityHolderComponent<BlockEntityBrewingStand> {
}
