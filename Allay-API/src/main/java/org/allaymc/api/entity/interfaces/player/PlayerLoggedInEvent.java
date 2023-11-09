package org.allaymc.api.entity.interfaces.player;

import org.allaymc.api.component.interfaces.ComponentEvent;

/**
 * Allay Project 2023/10/14
 *
 * @author daoge_cmd
 */
public record PlayerLoggedInEvent() implements ComponentEvent {
    static PlayerLoggedInEvent INSTANCE = new PlayerLoggedInEvent();
}
