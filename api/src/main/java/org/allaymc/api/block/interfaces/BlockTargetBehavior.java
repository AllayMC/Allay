package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityTarget;

/**
 * {@code BlockTargetBehavior} combines the contracts for a target block and its typed
 * target block entity. The inherited block behavior handles world operations, while the
 * holder contract gives callers access to target signal data stored separately from the
 * visible block state.
 */
public interface BlockTargetBehavior extends BlockBehavior, BlockBlockEntityHolderComponent<BlockEntityTarget> {
}
