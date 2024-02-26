package org.allaymc.api.entity.component.event;

import org.allaymc.api.event.Event;

/**
 * Allay Project 2023/10/14
 *
 * @author daoge_cmd
 */
public class PlayerLoggedInEvent extends Event {
    public static PlayerLoggedInEvent INSTANCE = new PlayerLoggedInEvent();

    /**
     *
     */
    private PlayerLoggedInEvent() {
    }
}
