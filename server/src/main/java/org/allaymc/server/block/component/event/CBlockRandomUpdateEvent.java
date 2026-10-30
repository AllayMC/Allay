package org.allaymc.server.block.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.eventbus.event.Event;

/**
 * {@code CBlockRandomUpdateEvent} carries a selected random block update to companion
 * components. The base behavior dispatches this internal event from its random-update
 * hook after the external update path has selected the block and permitted its
 * callback.
 *
 * @author IWareQ
 */
@Getter
@AllArgsConstructor
public class CBlockRandomUpdateEvent extends Event {
    /**
     * Block receiving random update.
     */
    protected Block block;
}
