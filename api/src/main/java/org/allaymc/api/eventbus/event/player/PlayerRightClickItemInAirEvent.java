package org.allaymc.api.eventbus.event.player;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;

/**
 * Called when a player right-clicks an item in air. This method is only called when the method
 * {@link ItemStack#canUseItemInAir(EntityPlayer)} of the item in player's hand return {@code false}.
 *
 * @author daoge_cmd
 */
@CallerThread(ThreadType.WORLD)
public class PlayerRightClickItemInAirEvent extends PlayerEvent implements CancellableEvent {
    public PlayerRightClickItemInAirEvent(EntityPlayer player) {
        super(player);
    }
}
