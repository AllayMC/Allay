package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;

/**
 * Called when a player changes the item they are holding in their hand.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class PlayerItemHeldEvent extends PlayerEvent {
    protected ItemStack itemStack;
    protected int slot;

    public PlayerItemHeldEvent(EntityPlayer player, ItemStack itemStack, int slot) {
        super(player);
        this.itemStack = itemStack;
        this.slot = slot;
    }
}
