package cn.allay.api.item;

import cn.allay.api.block.data.BlockFace;
import cn.allay.api.entity.interfaces.EntityPlayer;
import cn.allay.api.world.World;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

/**
 * Allay Project 2023/8/11
 *
 * @author daoge_cmd
 */
@FunctionalInterface
public interface UseItemOn {
    boolean useItemOn(@Nullable EntityPlayer player, ItemStack itemStack, World world, Vector3ic targetBlockPos, Vector3ic placeBlockPos, Vector3fc clickPos, BlockFace blockFace);
}
