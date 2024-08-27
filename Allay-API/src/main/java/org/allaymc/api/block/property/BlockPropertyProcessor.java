package org.allaymc.api.block.property;

import org.allaymc.api.block.PlaceBlockHelper;
import org.allaymc.api.block.poi.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.joml.Vector3ic;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.*;

/**
 * Allay Project 2024/8/18
 *
 * @author daoge_cmd
 */
@FunctionalInterface
public interface BlockPropertyProcessor {
    BlockState process(BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo);

    static void init() {
        PILLAR_AXIS.setProcessor(PlaceBlockHelper::processPillarAxisProperty);
        MINECRAFT_CARDINAL_DIRECTION.setProcessor(PlaceBlockHelper::processMinecraftCardinalDirectionProperty);
        FACING_DIRECTION.setProcessor(PlaceBlockHelper::processFacingDirectionProperty);
        GROUND_SIGN_DIRECTION.setProcessor(PlaceBlockHelper::processGroundSignDirectionProperty);
    }
}
