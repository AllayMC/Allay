package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;

/**
 * Called when a player changes the item they are holding in their hand.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class PlayerItemHeldEvent extends PlayerEvent implements CancellableEvent {

    /**
     * The item that the player was holding in their hand.
     */
    protected ItemStack oldItemStack;
    /**
     * The index of the hand slot that the player previously had selected.
     */
    protected int oldHandSlot;
    /**
     * The item that the player is holding in their hand.
     */
    protected ItemStack newItemStack;
    /**
     * The index of the hand slot that the player now has selected.
     */
    protected int newHandSlot;

    public PlayerItemHeldEvent(EntityPlayer player, ItemStack oldItemStack, int oldHandSlot, ItemStack newItemStack, int newHandSlot) {
        super(player);
        this.oldItemStack = oldItemStack;
        this.oldHandSlot = oldHandSlot;
        this.newItemStack = newItemStack;
        this.newHandSlot = newHandSlot;
    }
}
