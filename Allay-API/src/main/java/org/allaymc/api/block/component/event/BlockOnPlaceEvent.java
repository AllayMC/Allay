package org.allaymc.api.block.component.event;

import org.allaymc.api.block.data.BlockStateWithPos;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.component.interfaces.ComponentEvent;

/**
 * Allay Project 2023/9/15
 *
 * @author daoge_cmd
 */
public record BlockOnPlaceEvent(
        BlockStateWithPos currentBlockState,
        BlockState newBlockState
) implements ComponentEvent {
}
