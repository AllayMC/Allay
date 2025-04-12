package org.allaymc.api.block.dto;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.math.position.Position3ic;
import org.allaymc.api.world.Dimension;

/**
 * @param blockState the block state.
 * @param pos        the position of the block in the world.
 * @param layer      the layer where the block is located (default is 0).
 *
 * @author daoge_cmd
 */
public record BlockStateWithPos(
        BlockState blockState,
        Position3ic pos,
        int layer
) {

    /**
     * Constructs a new {@link BlockStateWithPos} with the given block state and position. The layer is defaulted to 0.
     *
     * @param blockState the block state.
     * @param pos        the position of the block.
     */
    public BlockStateWithPos(BlockState blockState, Position3ic pos) {
        this(blockState, pos, 0);
    }

    /**
     * Offsets the position with the given {@link BlockFace}, while leaving the other properties (block state and layer) unchanged.
     *
     * @param blockFace the {@link BlockFace} to offset the position by.
     *
     * @return a new {@link BlockStateWithPos} with the updated position.
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
     * Retrieves the dimension in which this block state is located.
     *
     * @return the {@link Dimension} of the block state.
     */
    public Dimension dimension() {
        return pos.dimension();
    }

    /**
     * Retrieves the block entity at the current position.
     *
     * @param <T> the type of the block entity.
     *
     * @return the block entity at the current position.
     */
    public <T extends BlockEntity> T getBlockEntity() {
        return (T) dimension().getBlockEntity(pos);
    }

    public <DATATYPE> void updateBlockProperty(BlockPropertyType<DATATYPE> propertyType, DATATYPE value) {
        dimension().updateBlockProperty(propertyType, value, pos, layer);
    }
}
