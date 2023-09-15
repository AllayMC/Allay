package cn.allay.api.block.component.event;

import cn.allay.api.block.data.BlockStateWithPos;
import cn.allay.api.block.type.BlockState;
import cn.allay.api.component.interfaces.ComponentEvent;

/**
 * Allay Project 2023/9/15
 *
 * @author daoge_cmd
 */
public record BlockOnPlaceEvent(BlockStateWithPos currentBlockState, BlockState newBlockState) implements ComponentEvent {
}
