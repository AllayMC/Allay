package org.allaymc.server.entity.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.eventbus.event.Event;
import org.allaymc.api.math.location.Location3dc;

/**
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class CEntitySetLocationEvent extends Event {
    protected final Location3dc location;
    protected final boolean calculateFallDistance;
}
