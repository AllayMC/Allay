package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Called when a page is turned on a lectern.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class LecternPageTurnEvent extends BlockEvent implements CancellableEvent {
    /**
     * The previous page number.
     */
    protected int oldPage;
    /**
     * The new page number.
     */
    @Setter
    protected int newPage;

    public LecternPageTurnEvent(Block block, int oldPage, int newPage) {
        super(block);
        this.oldPage = oldPage;
        this.newPage = newPage;
    }
}
