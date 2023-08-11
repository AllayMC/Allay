package cn.allay.api.block.blockupdate;

import cn.allay.api.block.data.BlockStateWithPos;
import cn.allay.api.block.data.BlockFace;

/**
 * Allay Project 2023/8/11
 *
 * @author daoge_cmd
 */
@FunctionalInterface
public interface OnNeighborChanged {
    void onNeighborChanged(BlockStateWithPos blockState, BlockStateWithPos neighborBlockState, BlockFace blockFace);
}
