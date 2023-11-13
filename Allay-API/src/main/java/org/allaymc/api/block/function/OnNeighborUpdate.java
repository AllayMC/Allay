package org.allaymc.api.block.function;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

/**
 * Allay Project 2023/8/11
 *
 * @author daoge_cmd
 */
@FunctionalInterface
public interface OnNeighborUpdate {
    /**
     * Call when a blockState causes another blockState to update.
     *
     * @param updated   The vec of the updated block
     * @param neighbor  The vec of the block that triggered the update
     * @param face      The neighbor block is on the side of the current updated block
     * @param dimension the dimension
     */
    void onNeighborUpdate(Vector3ic updated, Vector3ic neighbor, BlockFace face, Dimension dimension);
}
