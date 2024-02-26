package org.allaymc.api.block.component.event;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.event.Event;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

import java.util.Objects;

/**
 * Allay Project 2023/9/23
 *
 * @author daoge_cmd
 */
@Getter
public final class BlockOnInteractEvent extends Event {
    private final EntityPlayer player;
    private final ItemStack itemStack;
    private final Dimension dimension;
    private final Vector3ic blockPos;
    private final Vector3ic placeBlockPos;
    private final Vector3fc clickPos;
    private final BlockFace blockFace;
    @Setter
    private boolean success;

    /**
     *
     */
    public BlockOnInteractEvent(EntityPlayer player, ItemStack itemStack, Dimension dimension, Vector3ic blockPos,
                                Vector3ic placeBlockPos, Vector3fc clickPos,
                                BlockFace blockFace, boolean success) {
        this.player = player;
        this.itemStack = itemStack;
        this.dimension = dimension;
        this.blockPos = blockPos;
        this.placeBlockPos = placeBlockPos;
        this.clickPos = clickPos;
        this.blockFace = blockFace;
        this.success = success;
    }
}
