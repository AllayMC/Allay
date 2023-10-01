package cn.allay.api.block.component.event;

import cn.allay.api.block.data.BlockFace;
import cn.allay.api.block.data.BlockStateWithPos;
import cn.allay.api.component.interfaces.ComponentEvent;

/**
 * Allay Project 2023/9/23
 *
 * @author daoge_cmd
 */
public record BlockOnNeighborChangedEvent(
        BlockStateWithPos blockState,
        BlockStateWithPos neighborBlockState,
        BlockFace blockFace
) implements ComponentEvent {
}
