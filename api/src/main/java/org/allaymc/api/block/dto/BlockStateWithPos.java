package org.allaymc.api.block.dto;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.math.position.Position3ic;
import org.allaymc.api.world.Dimension;

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

    /**
     * Construct a new block state with the given block state and position. The layer is default to 0.
     *
     * @param blockState the block state.
     * @param pos        the position.
     */
    public BlockStateWithPos(BlockState blockState, Position3ic pos) {
        this(blockState, pos, 0);
    }

    /**
     * Offset the pos with the given {@link BlockFace}, and the other properties remain the same.
     *
     * @param blockFace the block face.
     *
     * @return the new block state with the offset pos.
     */
    public BlockStateWithPos offsetPos(BlockFace blockFace) {
        var offsetPos = blockFace.offsetPos(pos);
        return new BlockStateWithPos(
                pos.dimension().getBlockState(offsetPos, layer),
                new Position3i(offsetPos, pos.dimension()),
                layer
        );
    }

    /**
     * Get the dimension of the block state.
     *
     * @return the dimension.
     */
    public Dimension dimension() {
        return pos.dimension();
    }

    public <T extends BlockEntity> T getBlockEntity() {
        return (T) dimension().getBlockEntity(pos);
    }
}
