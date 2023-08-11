package cn.allay.api.block.blockupdate;

import cn.allay.api.block.data.BlockStateWithPos;
import cn.allay.api.block.type.BlockState;
import org.joml.Vector3i;

/**
 * Allay Project 2023/8/11
 *
 * @author daoge_cmd
 */
@FunctionalInterface
public interface OnPlace {
    void onPlace(BlockStateWithPos currentBlockState, BlockState newBlockState);
}
