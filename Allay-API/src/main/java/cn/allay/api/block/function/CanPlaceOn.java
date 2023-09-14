package cn.allay.api.block.function;

import cn.allay.api.block.data.BlockStateWithPos;

/**
 * Allay Project 2023/8/11
 *
 * @author daoge_cmd
 */
@FunctionalInterface
public interface CanPlaceOn {
    boolean canPlaceOn(BlockStateWithPos pos);
}
