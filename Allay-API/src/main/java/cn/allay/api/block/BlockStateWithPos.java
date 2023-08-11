package cn.allay.api.block;

import cn.allay.api.block.type.BlockState;
import org.joml.Vector3ic;

/**
 * Allay Project 2023/8/11
 *
 * @author daoge_cmd
 */
public record BlockStateWithPos(
        BlockState blockState,
        Vector3ic pos
) {
}
