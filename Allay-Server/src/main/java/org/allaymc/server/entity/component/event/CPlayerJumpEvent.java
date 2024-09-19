package org.allaymc.server.entity.component.event;

import lombok.AllArgsConstructor;
import org.allaymc.api.eventbus.event.Event;

/**
 * @author daoge_cmd
 */
@AllArgsConstructor
public class CPlayerJumpEvent extends Event {
    public static CPlayerJumpEvent INSTANCE = new CPlayerJumpEvent();
}
