package org.allaymc.api.block.function;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.entity.interfaces.player.EntityPlayer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.world.World;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

/**
 * Allay Project 2023/9/16
 *
 * @author daoge_cmd
 */
@FunctionalInterface
public interface OnInteract {
    boolean onInteract(@Nullable EntityPlayer player, ItemStack itemStack, World world, Vector3ic blockPos, Vector3ic placeBlockPos, Vector3fc clickPos, BlockFace blockFace);
}
