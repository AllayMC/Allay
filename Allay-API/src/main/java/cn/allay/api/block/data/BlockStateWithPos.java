package cn.allay.api.block.data;

import cn.allay.api.block.type.BlockState;
import cn.allay.api.math.position.Position3ic;

/**
 * Allay Project 2023/8/11
 *
 * @author daoge_cmd
 */
public record BlockStateWithPos(
        BlockState blockState,
        Position3ic pos,
        boolean layer
) {
}
