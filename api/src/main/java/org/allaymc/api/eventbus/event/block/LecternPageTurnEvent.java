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
    protected int oldPage;
    @Setter
    protected int newPage;

    public LecternPageTurnEvent(Block block, int oldPage, int newPage) {
        super(block);
        this.oldPage = oldPage;
        this.newPage = newPage;
    }
}
