package org.allaymc.api.entity.component;

import org.allaymc.api.block.type.BlockState;

/**
 * Allay Project 27/06/2024
 *
 * @author IWareQ
 */
public interface EntityFallingBlockBaseComponent extends EntityBaseComponent {
    BlockState getBlockState();

    void setBlockState(BlockState blockState);
}
