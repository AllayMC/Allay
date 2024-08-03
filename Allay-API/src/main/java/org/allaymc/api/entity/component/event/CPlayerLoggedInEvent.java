package org.allaymc.api.entity.component.event;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.allaymc.api.eventbus.event.Event;

/**
 * Allay Project 2023/10/14
 *
 * @author daoge_cmd
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CPlayerLoggedInEvent extends Event {
    public static final CPlayerLoggedInEvent INSTANCE = new CPlayerLoggedInEvent();
}
