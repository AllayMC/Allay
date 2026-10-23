package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;

/**
 * {@code PlayerItemHeldEvent} describes a proposed change between hotbar selections
 * and their item stacks. {@code EntityPlayer.setHandSlot} dispatches this event
 * before committing the selected slot; cancellation retains the old selection and
 * corrects the controlling client when the request originated there.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class PlayerItemHeldEvent extends PlayerEvent implements CancellableEvent {

    /**
     * {@code oldItemStack} retains the item in the previous hotbar selection. The
     * event exposes the container's mutable stack rather than copying its contents.
     */
    protected ItemStack oldItemStack;
    /**
     * Previous hotbar slot index.
     */
    protected int oldHandSlot;
    /**
     * {@code newItemStack} retains the item in the proposed hotbar selection. The
     * event exposes the container's mutable stack rather than copying its contents.
     */
    protected ItemStack newItemStack;
    /**
     * Proposed hotbar slot index.
     */
    protected int newHandSlot;

    /**
     * Creates a {@code PlayerItemHeldEvent} for the supplied player and event data.
     * The constructor retains reference-valued arguments without copying them and
     * does not dispatch or apply the represented action.
     */
    public PlayerItemHeldEvent(EntityPlayer player, ItemStack oldItemStack, int oldHandSlot, ItemStack newItemStack, int newHandSlot) {
        super(player);
        this.oldItemStack = oldItemStack;
        this.oldHandSlot = oldHandSlot;
        this.newItemStack = newItemStack;
        this.newHandSlot = newHandSlot;
    }
}
