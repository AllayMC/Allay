package cn.allay.api.block.blockupdate;

import cn.allay.api.block.BlockStateWithPos;

/**
 * Allay Project 2023/8/11
 *
 * @author daoge_cmd
 */
@FunctionalInterface
public interface OnScheduledUpdate {
    void onScheduledUpdate(BlockStateWithPos blockState);
}
