package org.allaymc.api.block.dto;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

/**
 * {@code PlayerInteractInfo} represents the player, target coordinates, and clicked
 * face associated with a block interaction. The record retains {@code
 * clickedBlockPos} and {@code clickedPos} without copying either vector, so callers
 * must preserve their coordinates when they need a stable interaction snapshot.
 * {@code getClickedBlock} resolves the target in the player's current dimension.
 *
 * @param player interacting player reference
 * @param clickedBlockPos clicked block coordinates
 * @param clickedPos fractional hit coordinates within the clicked block
 * @param blockFace clicked block face
 *
 * @author daoge_cmd
 */
public record PlayerInteractInfo(
        EntityPlayer player,
        Vector3ic clickedBlockPos,
        Vector3fc clickedPos,
        BlockFace blockFace
) {

    /**
     * Returns a block snapshot at {@code clickedBlockPos} in the player's current
     * dimension. The lookup uses the main block layer, and later player movement
     * between dimensions can change which dimension this method reads.
     */
    public Block getClickedBlock() {
        return new Block(player.getDimension(), clickedBlockPos);
    }
}
