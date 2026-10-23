package org.allaymc.server.entity.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.eventbus.event.Event;

/**
 * {@code CEntityFallEvent} carries the accepted landing distance from physics
 * to the entity's other components. The physics component dispatches it after
 * the public fall event succeeds, and living-component fall handling uses the
 * distance to calculate damage with the standing block's reduction.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class CEntityFallEvent extends Event {
    /**
     * Accepted landing distance measured in blocks.
     */
    protected double fallDistance;
}
