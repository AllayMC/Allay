package org.allaymc.api.block.dto;

import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.math.position.Position3ic;

/**
 * Represents a block state in the world.
 *
 * @param blockState the block state.
 * @param pos        the position.
 * @param layer      the layer.
 *
 * @author daoge_cmd
 */
public record BlockStateWithPos(
        BlockState blockState,
        Position3ic pos,
        int layer
) {
}
