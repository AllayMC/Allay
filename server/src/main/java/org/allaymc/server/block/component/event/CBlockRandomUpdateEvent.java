package org.allaymc.server.block.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.eventbus.event.Event;

/**
 * @author IWareQ
 */
@Getter
@AllArgsConstructor
public class CBlockRandomUpdateEvent extends Event {
    protected Block block;
}
