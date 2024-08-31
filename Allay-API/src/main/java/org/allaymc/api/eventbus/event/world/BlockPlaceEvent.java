package org.allaymc.api.eventbus.event.world;

import lombok.Getter;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

/**
 * Allay Project 2024/8/31
 *
 * @author daoge_cmd
 */
@Getter
public class BlockPlaceEvent extends WorldEvent implements CancellableEvent {

    protected Dimension dimension;
    protected Vector3ic placeBlockPos;
    protected BlockState blockPlaced;
    protected BlockState blockReplaced;
    /**
     * can be null
     */
    protected ItemStack itemUsed;
    /**
     * can be null
     */
    protected EntityPlayer player;
    /**
     * can be null
     */
    protected PlayerInteractInfo interactInfo;

    public BlockPlaceEvent(Dimension dimension, Vector3ic placeBlockPos, BlockState blockPlaced, BlockState blockReplaced, ItemStack itemUsed, EntityPlayer player, PlayerInteractInfo interactInfo) {
        super(dimension.getWorld());
        this.dimension = dimension;
        this.placeBlockPos = placeBlockPos;
        this.blockPlaced = blockPlaced;
        this.blockReplaced = blockReplaced;
        this.itemUsed = itemUsed;
        this.player = player;
        this.interactInfo = interactInfo;
    }
}
