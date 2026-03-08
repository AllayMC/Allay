package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityArrow;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;

/**
 * Called when a player is about to pick up an arrow entity from the ground.
 * <p>
 * This event is called before the arrow is added to the player's inventory.
 * Cancelling this event will prevent the arrow from being picked up.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class PlayerPickupArrowEvent extends PlayerEvent implements CancellableEvent {
    /**
     * The arrow entity being picked up.
     */
    protected EntityArrow arrow;
    /**
     * The arrow item that will be added to the player's inventory.
     */
    protected ItemStack arrowItem;

    public PlayerPickupArrowEvent(EntityPlayer player, EntityArrow arrow, ItemStack arrowItem) {
        super(player);
        this.arrow = arrow;
        this.arrowItem = arrowItem;
    }
}
