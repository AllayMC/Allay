package org.allaymc.api.block.property;

import org.allaymc.api.block.BlockPlaceHelper;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.joml.Vector3ic;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.*;

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
    static void init() {
        PILLAR_AXIS.setProcessor(BlockPlaceHelper::processPillarAxisProperty);
        MINECRAFT_CARDINAL_DIRECTION.setProcessor(BlockPlaceHelper::processMinecraftCardinalDirectionProperty);
        FACING_DIRECTION.setProcessor(BlockPlaceHelper::processFacingDirectionProperty);
        GROUND_SIGN_DIRECTION.setProcessor(BlockPlaceHelper::processGroundSignDirectionProperty);
        UPSIDE_DOWN_BIT.setProcessor(BlockPlaceHelper::processUpsideDownBitProperty);
    }

    /**
     * Process the block state based on the placement information.
     *
     * @param blockState    the block state to process.
     * @param placeBlockPos the position where the block is placed.
     * @param placementInfo the placement information.
     *
     * @return the processed block state.
     */
    BlockState process(BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo);
}
