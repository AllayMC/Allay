package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityItem;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Called when a player is about to pick up an item entity from the ground.
 * <p>
 * This event is called before the item is added to the player's inventory.
 * Cancelling this event will prevent the item from being picked up.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class PlayerPickupItemEvent extends PlayerEvent implements CancellableEvent {
    /**
     * The item entity being picked up.
     */
    protected EntityItem itemEntity;

    public PlayerPickupItemEvent(EntityPlayer player, EntityItem itemEntity) {
        super(player);
        this.itemEntity = itemEntity;
    }
}
