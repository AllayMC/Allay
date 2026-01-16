package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;
import org.joml.Vector3fc;

/**
 * Event called when a player interacts with an entity.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class PlayerInteractEntityEvent extends PlayerEvent implements CancellableEvent {

    /**
     * The entity that the player is interacting with.
     */
    protected Entity entity;
    /**
     * The item stack that the player is used to interact with the entity.
     */
    protected ItemStack itemStack;
    /**
     * The position where the player clicked on the entity.
     */
    protected Vector3fc clickPos;

    public PlayerInteractEntityEvent(EntityPlayer player, Entity entity, ItemStack itemStack, Vector3fc clickPos) {
        super(player);
        this.entity = entity;
        this.itemStack = itemStack;
        this.clickPos = clickPos;
    }
}
