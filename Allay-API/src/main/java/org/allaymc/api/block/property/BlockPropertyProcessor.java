package org.allaymc.api.block.property;

import org.allaymc.api.block.component.common.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

/**
 * Allay Project 2024/7/24
 *
 * @author daoge_cmd
 */
@FunctionalInterface
public interface BlockPropertyProcessor {
    BlockState process(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo);
}
