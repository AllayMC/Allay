package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;

/**
 * {@code LecternDropBookEvent} represents a player's request to eject the book
 * stored in a lectern. The lectern component dispatches this event on a player
 * punch while a book is present, and cancellation preserves that book in the
 * lectern.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class LecternDropBookEvent extends BlockEvent implements CancellableEvent {
    /**
     * Player requesting the book ejection.
     */
    protected EntityPlayer player;
    /**
     * {@code book} references the lectern's stored stack at dispatch. The
     * generated getter exposes the same mutable item to listeners before ejection.
     */
    protected ItemStack book;

    /**
     * Creates a lectern book ejection request retaining the block, player, and
     * book references. Construction leaves the lectern unchanged; the caller
     * dispatches the event before deciding whether to drop the book.
     */
    public LecternDropBookEvent(Block block, EntityPlayer player, ItemStack book) {
        super(block);
        this.player = player;
        this.book = book;
    }
}
