package org.allaymc.api.block.dto;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

/**
 * Allay Project 2024/6/20
 *
 * @param player        The player who is placing the block
 * @param clickBlockPos The block that the player clicked on
 * @param clickPos      The precise pos where the player clicked
 * @param blockFace     The face of the block that the player clicked on
 *
 * @author daoge_cmd
 */
public record PlayerInteractInfo(
        EntityPlayer player,
        Vector3ic clickBlockPos,
        Vector3fc clickPos,
        BlockFace blockFace
) {
}
