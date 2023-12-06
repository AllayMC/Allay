package org.allaymc.api.entity.component.event;

import org.allaymc.api.component.interfaces.ComponentEvent;

/**
 * Allay Project 2023/10/14
 *
 * @author daoge_cmd
 */
public record PlayerLoggedInEvent() implements ComponentEvent {
    public static PlayerLoggedInEvent INSTANCE = new PlayerLoggedInEvent();
}
