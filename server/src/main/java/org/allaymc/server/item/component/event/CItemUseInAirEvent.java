package org.allaymc.server.item.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.Event;

/**
 * {@code CItemUseInAirEvent} carries a completed timed-use attempt to item
 * components with its player and elapsed game ticks. The base component starts
 * {@code canBeUsed} at {@code false} and returns the result available when
 * dispatch returns.
 *
 * @author daoge_cmd
 */
@Getter
@Setter
@AllArgsConstructor
public class CItemUseInAirEvent extends Event {
    /**
     * Player completing timed item use.
     */
    protected EntityPlayer player;
    /**
     * Elapsed use time in game ticks.
     */
    protected long usingTime;
    /**
     * {@code canBeUsed} holds the completed-use result returned by the base
     * component when dispatch returns. Later handlers can replace an earlier
     * handler's result.
     */
    protected boolean canBeUsed;
}
