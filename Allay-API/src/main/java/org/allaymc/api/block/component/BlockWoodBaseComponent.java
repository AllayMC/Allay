package org.allaymc.api.block.component;

import org.allaymc.api.block.type.BlockState;

/**
 * Allay Project 2024/8/18
 *
 * @author daoge_cmd
 */
public interface BlockWoodBaseComponent extends BlockBaseComponent {

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
