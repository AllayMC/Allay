package org.allaymc.server.item.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.Event;

/**
 * {@code CItemBreakBlockEvent} carries the broken block state and breaking entity
 * to an item's component handlers. The event retains these supplied values for
 * notification; changing its generated properties changes the event seen by
 * later handlers rather than performing another block break.
 *
 * @author daoge_cmd
 */
@Getter
@Setter
@AllArgsConstructor
public class CItemBreakBlockEvent extends Event {
    /**
     * Block state associated with the break.
     */
    protected BlockState block;
    /**
     * Entity performing the block break.
     */
    protected Entity breaker;
}
