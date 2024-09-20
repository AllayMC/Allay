package org.allaymc.server.entity.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.eventbus.event.Event;
import org.allaymc.api.math.location.Location3fc;

/**
 * @author daoge_cmd
 */
@AllArgsConstructor
@Getter
public class CPlayerMoveEvent extends Event {
    protected Location3fc newLoc;
}
