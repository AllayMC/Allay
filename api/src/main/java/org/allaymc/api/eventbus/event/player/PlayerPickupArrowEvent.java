package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityArrow;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;

/**
 * {@code PlayerPickupArrowEvent} describes an arrow item about to enter a player's
 * inventory from a ground entity. The pickup component dispatches it before
 * inserting {@code arrowItem}; cancellation keeps that path from inserting the item
 * or removing the arrow entity.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class PlayerPickupArrowEvent extends PlayerEvent implements CancellableEvent {
    /**
     * Ground arrow entity being considered.
     */
    protected EntityArrow arrow;
    /**
     * {@code arrowItem} retains the candidate item stack. The pickup component
     * inserts this same mutable object after event approval.
     */
    protected ItemStack arrowItem;

    /**
     * Creates a {@code PlayerPickupArrowEvent} for the supplied player and event
     * data. The constructor retains reference-valued arguments without copying them
     * and does not dispatch or apply the represented action.
     */
    public PlayerPickupArrowEvent(EntityPlayer player, EntityArrow arrow, ItemStack arrowItem) {
        super(player);
        this.arrow = arrow;
        this.arrowItem = arrowItem;
    }
}
