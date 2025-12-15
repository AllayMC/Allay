package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;

/**
 * Called when a player drops an item from their inventory.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class PlayerDropItemEvent extends PlayerEvent implements CancellableEvent {
    protected ItemStack itemStack;

    public PlayerDropItemEvent(EntityPlayer player, ItemStack itemStack) {
        super(player);
        this.itemStack = itemStack;
    }
}
