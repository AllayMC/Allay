package cn.allay.api.block.component.event;

import cn.allay.api.block.data.BlockFace;
import cn.allay.api.component.interfaces.ComponentEvent;
import cn.allay.api.entity.interfaces.player.EntityPlayer;
import cn.allay.api.item.ItemStack;
import cn.allay.api.world.World;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

/**
 * Allay Project 2023/9/23
 *
 * @author daoge_cmd
 */
public record BlockOnInteractEvent(@Nullable EntityPlayer player, ItemStack itemStack, World world, Vector3ic blockPos,
                                   Vector3ic placeBlockPos, Vector3fc clickPos,
                                   BlockFace blockFace) implements ComponentEvent {
}
