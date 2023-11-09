package org.allaymc.api.block.function;

import org.allaymc.api.block.data.BlockStateWithPos;
import org.allaymc.api.block.type.BlockState;

/**
 * Allay Project 2023/8/11
 *
 * @author daoge_cmd
 */
@FunctionalInterface
public interface OnPlace {
    void onPlace(BlockStateWithPos currentBlockState, BlockState newBlockState);
}
