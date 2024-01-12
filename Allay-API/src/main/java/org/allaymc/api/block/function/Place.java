package org.allaymc.api.block.function;

import com.google.common.base.Preconditions;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

/**
 * Allay Project 2023/9/13
 *
 * @author Cool_Loong
 */
@FunctionalInterface
public interface Place {
    static void checkParam(EntityPlayer player, Dimension dimension, BlockState blockState, Vector3ic targetBlockPos, Vector3ic placeBlockPos, Vector3fc clickPos, BlockFace blockFace) {
        // player is nullable
        Preconditions.checkNotNull(dimension);
        Preconditions.checkNotNull(blockState);
        Preconditions.checkNotNull(targetBlockPos);
        Preconditions.checkNotNull(placeBlockPos);
        // clickPos is nullable
        Preconditions.checkNotNull(blockFace);
    }

    boolean place(EntityPlayer player, Dimension dimension, BlockState blockState, Vector3ic targetBlockPos, Vector3ic placeBlockPos, Vector3fc clickPos, BlockFace blockFace);
}
