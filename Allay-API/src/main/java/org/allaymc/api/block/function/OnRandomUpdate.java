package org.allaymc.api.block.function;

import org.allaymc.api.block.data.BlockStateWithPos;

/**
 * Allay Project 2023/8/11
 *
 * @author daoge_cmd
 */
@FunctionalInterface
public interface OnRandomUpdate {
    void onRandomUpdate(BlockStateWithPos blockState);
}
