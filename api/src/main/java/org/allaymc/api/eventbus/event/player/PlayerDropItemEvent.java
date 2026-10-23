package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;

/**
 * {@code PlayerDropItemEvent} describes a player's proposed removal of items from an
 * inventory slot. {@code EntityPlayer.forceDropItem} dispatches it before reducing
 * or clearing the slot; cancellation keeps the slot contents and causes a slot
 * notification to restore the client view.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class PlayerDropItemEvent extends PlayerEvent implements CancellableEvent {
    /**
     * {@code itemStack} retains the source slot's mutable stack before the requested
     * count is removed; it is not a detached dropped stack.
     */
    protected ItemStack itemStack;

    /**
     * Creates a {@code PlayerDropItemEvent} for the supplied player and event data.
     * The constructor retains reference-valued arguments without copying them and
     * does not dispatch or apply the represented action.
     */
    public PlayerDropItemEvent(EntityPlayer player, ItemStack itemStack) {
        super(player);
        this.itemStack = itemStack;
    }
}
