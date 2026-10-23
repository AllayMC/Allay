package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityDecoratedPot;

/**
 * {@code BlockDecoratedPotBehavior} exposes general block operations and block entity
 * access for decorated pot blocks. The inherited holder contract supplies the block entity
 * type and location lookup, including creation when the corresponding block entity is
 * absent.
 */
public interface BlockDecoratedPotBehavior extends BlockBehavior, BlockBlockEntityHolderComponent<BlockEntityDecoratedPot> {
}
