package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;

/**
 * Called when a player attempts to place a book on a lectern.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class LecternPlaceBookEvent extends BlockEvent implements CancellableEvent {
    protected EntityPlayer player;
    protected ItemStack book;

    public LecternPlaceBookEvent(Block block, EntityPlayer player, ItemStack book) {
        super(block);
        this.player = player;
        this.book = book;
    }
}
