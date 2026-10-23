package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.interfaces.ItemWritableBookStack;

/**
 * {@code PlayerBookEditEvent} describes a writable-book operation requested by a
 * player. The processor dispatches this event before editing pages or replacing the
 * book when signing; cancellation prevents that operation, while {@code book}
 * exposes the existing mutable stack rather than a snapshot.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class PlayerBookEditEvent extends PlayerEvent implements CancellableEvent {

    /**
     * {@code book} retains the writable stack being edited. Listeners observe the
     * caller's mutable item rather than a separate snapshot.
     */
    protected final ItemWritableBookStack book;
    /**
     * Requested book editing operation.
     */
    protected final Action action;

    /**
     * Creates a {@code PlayerBookEditEvent} for the supplied player and event data.
     * The constructor retains reference-valued arguments without copying them and
     * does not dispatch or apply the represented action.
     */
    public PlayerBookEditEvent(EntityPlayer player, ItemWritableBookStack book, Action action) {
        super(player);
        this.book = book;
        this.action = action;
    }

    /**
     * {@code Action} identifies the page-edit or signing operation requested for a
     * writable book. These constants let listeners distinguish the processor's
     * selected edit before it changes the book.
     */
    public enum Action {
        /**
         * Replace existing page text.
         */
        REPLACE_PAGE,
        /**
         * Insert or append a page.
         */
        ADD_PAGE,
        /**
         * Remove a selected page.
         */
        DELETE_PAGE,
        /**
         * Exchange two selected pages.
         */
        SWAP_PAGES,
        /**
         * Replace the writable book with a signed book.
         */
        SIGN_BOOK
    }
}
