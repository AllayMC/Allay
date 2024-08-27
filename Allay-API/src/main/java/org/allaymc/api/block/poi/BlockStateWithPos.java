package org.allaymc.api.block.poi;

import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.math.position.Position3ic;

/**
 * Allay Project 2023/8/11
 *
 * @author daoge_cmd
 */
public record BlockStateWithPos(
        BlockState blockState,
        Position3ic pos,
        int layer
) {
}
