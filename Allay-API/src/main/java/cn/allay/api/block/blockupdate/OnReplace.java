package cn.allay.api.block.blockupdate;

import cn.allay.api.block.data.BlockStateWithPos;
import cn.allay.api.block.type.BlockState;

/**
 * Allay Project 2023/8/11
 *
 * @author daoge_cmd
 */
@FunctionalInterface
public interface OnReplace {
    void onReplace(BlockStateWithPos currentBlockState, BlockState newBlockState);
}
