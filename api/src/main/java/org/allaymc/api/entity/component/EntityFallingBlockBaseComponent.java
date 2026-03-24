package org.allaymc.api.entity.component;

import org.allaymc.api.block.type.BlockState;

/**
 * Shared component for falling block entities that carry the represented block state.
 *
 * @author IWareQ
 */
public interface EntityFallingBlockBaseComponent extends EntityBaseComponent {
    /**
     * Get the block state of this entity falling block.
     *
     * @return the block state
     */
    BlockState getBlockState();

    /**
     * Set the block state of this entity falling block.
     *
     * @param blockState the block state
     */
    void setBlockState(BlockState blockState);
}
