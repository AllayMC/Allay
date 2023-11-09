package org.allaymc.api.block.function;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.entity.interfaces.player.EntityPlayer;
import org.allaymc.api.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

/**
 * Allay Project 2023/9/13
 *
 * @author Cool_Loong
 */
@FunctionalInterface
public interface Place {
    boolean place(@Nullable EntityPlayer player, @NotNull World world, @NotNull BlockState blockState, @NotNull Vector3ic targetBlockPos, @NotNull Vector3ic placeBlockPos, @NotNull Vector3fc clickPos, @NotNull BlockFace blockFace);
}
