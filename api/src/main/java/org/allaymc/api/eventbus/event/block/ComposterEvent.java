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
 * Called when a player attempts to add an item to a composter.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class ComposterEvent extends BlockEvent implements CancellableEvent {
    protected EntityPlayer player;
    protected ItemStack item;
    @Setter
    protected int currentFillLevel;

    public ComposterEvent(Block block, EntityPlayer player, ItemStack item, int currentFillLevel) {
        super(block);
        this.player = player;
        this.item = item;
        this.currentFillLevel = currentFillLevel;
    }
}
