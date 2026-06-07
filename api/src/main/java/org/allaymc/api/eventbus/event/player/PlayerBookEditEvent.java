package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.interfaces.ItemWritableBookStack;

/**
 * Event triggered when a player edits a book.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class PlayerBookEditEvent extends PlayerEvent implements CancellableEvent {

    /**
     * The book being edited.
     */
    protected final ItemWritableBookStack book;
    /**
     * The edit action being performed.
     */
    protected final Action action;

    public PlayerBookEditEvent(EntityPlayer player, ItemWritableBookStack book, Action action) {
        super(player);
        this.book = book;
        this.action = action;
    }

    /**
     * The type of book edit operation.
     */
    public enum Action {
        REPLACE_PAGE,
        ADD_PAGE,
        DELETE_PAGE,
        SWAP_PAGES,
        SIGN_BOOK
    }
}
