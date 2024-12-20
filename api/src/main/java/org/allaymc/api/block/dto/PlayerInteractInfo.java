package org.allaymc.api.block.dto;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

/**
 * Represents the information about a player interacting with a block.
 *
 * @param player          The player who is placing the block.
 * @param clickedBlockPos The block that the player clicked on.
 * @param clickedPos      The precise pos where the player clicked.
 * @param blockFace       The face of the block that the player clicked on.
 *
 * @author daoge_cmd
 */
public record PlayerInteractInfo(
        EntityPlayer player,
        Vector3ic clickedBlockPos,
        Vector3fc clickedPos,
        BlockFace blockFace
) {

    public BlockState getClickedBlockState() {
        return player.getDimension().getBlockState(clickBlockPos);
    }
}
