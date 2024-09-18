package org.allaymc.api.entity.component;

import org.allaymc.api.block.type.BlockState;

/**
 * @author IWareQ
 */
public interface EntityFallingBlockBaseComponent extends EntityBaseComponent {
    BlockState getBlockState();

    void setBlockState(BlockState blockState);
}
