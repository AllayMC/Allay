package org.allaymc.api.block.component.event;

import org.allaymc.api.block.data.BlockStateWithPos;
import org.allaymc.api.block.type.BlockState;

/**
 * Allay Project 2023/9/15
 *
 * @author daoge_cmd
 */
public record BlockOnReplaceEvent(
        BlockStateWithPos currentBlockState,
        BlockState newBlockState
) {
}
