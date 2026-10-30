package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;

/**
 * {@code ComposterEvent} represents a player's attempt to add a compostable item
 * to a composter. The interaction handler dispatches it before consuming the item
 * or rolling the compost chance, and cancellation prevents both actions. The
 * handler currently uses its original local fill level after dispatch.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class ComposterEvent extends BlockEvent implements CancellableEvent {
    /**
     * Player attempting to add compost.
     */
    protected EntityPlayer player;
    /**
     * {@code item} references the interaction stack passed by the caller. The
     * generated getter exposes that same mutable stack to event listeners.
     */
    protected ItemStack item;
    /**
     * {@code currentFillLevel} records the fill level before composting. The
     * built-in interaction handler computes its next level from the original local
     * value, so the generated setter changes only the event value.
     */
    @Setter
    protected int currentFillLevel;

    /**
     * Creates a composter attempt retaining the block, player, and item
     * references. {@code currentFillLevel} is the observed level before adding
     * compost; construction neither consumes the item nor changes the composter.
     */
    public ComposterEvent(Block block, EntityPlayer player, ItemStack item, int currentFillLevel) {
        super(block);
        this.player = player;
        this.item = item;
        this.currentFillLevel = currentFillLevel;
    }
}
