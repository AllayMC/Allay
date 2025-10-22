package org.allaymc.server.block.component.event;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.allaymc.api.eventbus.event.Event;

/**
 * @author daoge_cmd
 */
@Getter
@Setter
@Accessors(fluent = true)
public class CBlockTryRandomUpdateEvent extends Event {
    protected boolean canRandomUpdate;
}
