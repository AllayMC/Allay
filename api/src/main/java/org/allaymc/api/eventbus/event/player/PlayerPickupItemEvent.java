package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityItem;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code PlayerPickupItemEvent} describes a ground item entity considered for a
 * player's inventory. The pickup component dispatches it before transferring its
 * stack; cancellation skips this pickup attempt, while an accepted attempt may still
 * leave items behind when inventory space is insufficient.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class PlayerPickupItemEvent extends PlayerEvent implements CancellableEvent {
    /**
     * Ground item entity being considered.
     */
    protected EntityItem itemEntity;

    /**
     * Creates a {@code PlayerPickupItemEvent} for the supplied player and event
     * data. The constructor retains reference-valued arguments without copying them
     * and does not dispatch or apply the represented action.
     */
    public PlayerPickupItemEvent(EntityPlayer player, EntityItem itemEntity) {
        super(player);
        this.itemEntity = itemEntity;
    }
}
