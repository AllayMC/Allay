package org.allaymc.server.block.component.event;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.allaymc.api.eventbus.event.Event;

/**
 * {@code CBlockTryRandomUpdateEvent} collects a block behavior's current request for
 * random updates. The base component creates it with a {@code false} flag, dispatches
 * it to companion components, and returns the final flag value rather than combining
 * independent handler results.
 *
 * @author daoge_cmd
 */
@Getter
@Setter
@Accessors(fluent = true)
public class CBlockTryRandomUpdateEvent extends Event {
    /**
     * {@code canRandomUpdate} starts as {@code false}. Each fluent setter replaces
     * the flag, so a later handler can clear an earlier request.
     */
    protected boolean canRandomUpdate;
}
