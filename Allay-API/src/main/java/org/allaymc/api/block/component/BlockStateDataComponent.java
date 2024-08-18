package org.allaymc.api.block.component;

import org.allaymc.api.block.BlockStateData;
import org.allaymc.api.block.type.BlockState;

/**
 * Allay Project 2023/3/25
 *
 * @author daoge_cmd | CoolLoong
 */
public interface BlockStateDataComponent extends BlockComponent {
    BlockStateData getBlockStateData(BlockState blockState);
}
