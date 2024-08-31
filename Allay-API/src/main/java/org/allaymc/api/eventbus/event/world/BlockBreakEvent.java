package org.allaymc.api.eventbus.event.world;

import lombok.Getter;
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
public class BlockBreakEvent extends WorldEvent implements CancellableEvent {
    protected Dimension dimension;
    protected Vector3ic blockPos;
    /**
     * Can be null
     */
    protected ItemStack itemUsed;
    /**
     * Can be null
     */
    protected EntityPlayer player;

    public BlockBreakEvent(Dimension dimension, Vector3ic blockPos, ItemStack itemUsed, EntityPlayer player) {
        super(dimension.getWorld());
        this.dimension = dimension;
        this.blockPos = blockPos;
        this.itemUsed = itemUsed;
        this.player = player;
    }
}
