package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;

/**
 * {@code LecternPlaceBookEvent} represents a player's attempt to place a book on
 * an empty lectern. The lectern component supplies a copy with count one before
 * consuming the held item, and cancellation prevents both consumption and
 * installation of the copied book.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class LecternPlaceBookEvent extends BlockEvent implements CancellableEvent {
    /**
     * Player placing the lectern book.
     */
    protected EntityPlayer player;
    /**
     * {@code book} references the proposed single-book stack. The lectern
     * component passes this same mutable instance to its block entity after
     * successful event dispatch.
     */
    protected ItemStack book;

    /**
     * Creates a book placement request retaining the supplied block, player, and
     * book references. The lectern component prepares the single-book copy before
     * construction and installs it only after successful dispatch.
     */
    public LecternPlaceBookEvent(Block block, EntityPlayer player, ItemStack book) {
        super(block);
        this.player = player;
        this.book = book;
    }
}
