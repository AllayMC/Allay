package org.allaymc.server.entity.component.event;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.allaymc.api.eventbus.event.Event;

/**
 * @author daoge_cmd
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CPlayerLoggedInEvent extends Event {
    public static final CPlayerLoggedInEvent INSTANCE = new CPlayerLoggedInEvent();
}
