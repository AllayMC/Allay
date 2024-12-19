package org.allaymc.api.block.component;

import org.allaymc.api.block.type.BlockState;

/**
 * @author IWareQ
 */
public interface BlockStrippableComponent extends BlockComponent {
    /**
     * Get the stripped block state of the given block state
     *
     * @param unstrippedBlockState the unstripped block state
     *
     * @return the stripped block state
     */
    BlockState getStrippedBlockState(BlockState unstrippedBlockState);

    /**
     * Check if the block state is stripped
     *
     * @param blockState the block state
     *
     * @return true if the block state is stripped, false otherwise
     */
    boolean isStripped(BlockState blockState);
}
