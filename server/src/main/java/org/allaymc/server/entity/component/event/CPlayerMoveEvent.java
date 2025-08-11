package org.allaymc.server.entity.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.eventbus.event.Event;

/**
 * @author daoge_cmd
 */
@AllArgsConstructor
@Getter
public class CPlayerMoveEvent extends Event {
    public static final CPlayerMoveEvent INSTANCE = new CPlayerMoveEvent();
}
