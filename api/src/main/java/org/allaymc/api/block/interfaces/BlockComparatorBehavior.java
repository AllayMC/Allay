package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityComparator;

/**
 * {@code BlockComparatorBehavior} combines the contracts for a comparator block and its
 * typed comparator block entity. The inherited block behavior handles world operations,
 * while the holder contract gives callers access to comparator output stored separately
 * from the visible block state.
 */
public interface BlockComparatorBehavior extends BlockBehavior, BlockBlockEntityHolderComponent<BlockEntityComparator> {
}
