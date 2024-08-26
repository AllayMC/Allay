package org.allaymc.server.entity.component.event;

import lombok.AllArgsConstructor;
import org.allaymc.api.eventbus.event.Event;

/**
 * Allay Project 2024/8/26
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
public class CPlayerJumpEvent extends Event {
    public static CPlayerJumpEvent INSTANCE = new CPlayerJumpEvent();
}
