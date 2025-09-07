package org.allaymc.server.block.property;

import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.joml.Vector3ic;

/**
 * Represents a processor for a specific block property.
 * <p>
 * Block property processor is used when placing a block, to indicate the correct value for block property
 * base on the placement information.
 * <br>
 * This prevents writing a lot of complicated code for the blocks which are using the same block property type.
 *
 * @author daoge_cmd
 */
@FunctionalInterface
public interface BlockPropertyProcessor {
    /**
     * Process the block state based on the placement information.
     *
     * @param blockState    the block state to process
     * @param placeBlockPos the position where the block is placed
     * @param placementInfo the placement information
     * @return the processed block state
     */
    BlockState process(BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo);
}
