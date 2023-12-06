package org.allaymc.api.block.function;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.world.Dimension;
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
    /**
     *
     * @param player The player who interacted with the block
     * @param itemStack The item in the player's hand
     * @param dimension The dimension of the block & player
     * @param blockPos The pos of the block that the player clicked on
     * @param placeBlockPos Assuming the player is holding a block item in their hand, this parameter indicates where the block will be placed (if it can be placed)
     * @param clickPos The precise pos where the player clicked
     * @param blockFace The face of the block that the player clicked on
     * @return Whether the operation is valid.
     *         For example, right-clicking on the crafting table is normally considered a valid operation, so this method will return true
     *         If false is returned, the useItemOn method of the player's item will continue to be called
     */
    boolean onInteract(@Nullable EntityPlayer player, ItemStack itemStack, Dimension dimension, Vector3ic blockPos, Vector3ic placeBlockPos, Vector3fc clickPos, BlockFace blockFace);
}
