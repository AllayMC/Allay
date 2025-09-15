package org.allaymc.api.block.component;

import org.allaymc.api.block.data.BlockStateData;
import org.allaymc.api.block.type.BlockState;

/**
 * @author daoge_cmd | CoolLoong
 */
public interface BlockStateDataComponent extends BlockComponent {
    /**
     * Retrieves the {@link BlockStateData} associated with the specified {@link BlockState}.
     *
     * @param blockState the block state
     * @return the block state data for the given state
     */
    BlockStateData getBlockStateData(BlockState blockState);
}
