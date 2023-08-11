package cn.allay.api.block.blockupdate;

import cn.allay.api.block.data.BlockStateWithPos;

/**
 * Allay Project 2023/8/11
 *
 * @author daoge_cmd
 */
@FunctionalInterface
public interface OnRedStoneUpdate {
    void onRedStoneUpdate(BlockStateWithPos blockState);
}
