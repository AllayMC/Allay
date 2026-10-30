package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.eventbus.event.Event;

/**
 * {@code BlockEvent} carries the block snapshot associated with a block-related
 * event. Specialized event types add their own context, such as an incoming
 * replacement state or an interacting entity, while this base type exposes the common
 * block reference.
 *
 * @author daoge_cmd
 */
@Getter
public abstract class BlockEvent extends Event {
    /**
     * Associated block event snapshot.
     */
    protected Block block;

    /**
     * Creates a block event retaining the supplied block snapshot reference.
     */
    public BlockEvent(Block block) {
        this.block = block;
    }
}
