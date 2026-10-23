package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code LecternPageTurnEvent} represents a proposed page change for a lectern
 * that contains a book. The lectern component skips unchanged requests, then
 * dispatches this event before setting the final page and emitting its redstone
 * pulse. Handlers can cancel or replace the requested page.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class LecternPageTurnEvent extends BlockEvent implements CancellableEvent {
    /**
     * Previous zero-based book page index.
     */
    protected int oldPage;
    /**
     * {@code newPage} is the requested zero-based book page index. The lectern
     * component passes the final event value to the block entity's page setter.
     */
    @Setter
    protected int newPage;

    /**
     * Creates a page change proposal from the old and requested page indices.
     * Construction stores the supplied indices without validation; listeners can
     * replace {@code newPage} before the lectern component applies it.
     */
    public LecternPageTurnEvent(Block block, int oldPage, int newPage) {
        super(block);
        this.oldPage = oldPage;
        this.newPage = newPage;
    }
}
