package org.allaymc.api.block.component.data;

import org.allaymc.api.block.component.BlockComponent;
import org.allaymc.api.block.type.BlockState;

/**
 * @author daoge_cmd | CoolLoong
 */
public interface BlockStateDataComponent extends BlockComponent {
    /**
     * Get the block state data of the specific block state.
     *
     * @param blockState the block state.
     *
     * @return the block state data of the provided block state.
     */
    BlockStateData getBlockStateData(BlockState blockState);
}
