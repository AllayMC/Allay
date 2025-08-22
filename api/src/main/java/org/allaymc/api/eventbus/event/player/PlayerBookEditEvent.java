package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.interfaces.ItemWritableBookStack;
import org.cloudburstmc.protocol.bedrock.packet.BookEditPacket;

/**
 * Event triggered when a player edits a book.
 *
 * @author daoge_cmd
 */
@Getter
public class PlayerBookEditEvent extends PlayerEvent implements CancellableEvent {

    protected final ItemWritableBookStack book;
    protected final BookEditPacket.Action action;

    public PlayerBookEditEvent(EntityPlayer player, ItemWritableBookStack book, BookEditPacket.Action action) {
        super(player);
        this.book = book;
        this.action = action;
    }
}
