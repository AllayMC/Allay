package org.allaymc.api.block.property;

import org.allaymc.api.block.PlaceBlockHelper;
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
 * <p>
 * Allay Project 2024/8/18
 *
 * @author daoge_cmd
 */
@FunctionalInterface
public interface BlockPropertyProcessor {
    static void init() {
        PILLAR_AXIS.setProcessor(PlaceBlockHelper::processPillarAxisProperty);
        MINECRAFT_CARDINAL_DIRECTION.setProcessor(PlaceBlockHelper::processMinecraftCardinalDirectionProperty);
        FACING_DIRECTION.setProcessor(PlaceBlockHelper::processFacingDirectionProperty);
        GROUND_SIGN_DIRECTION.setProcessor(PlaceBlockHelper::processGroundSignDirectionProperty);
    }

    BlockState process(BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo);
}
