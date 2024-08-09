package org.allaymc.api.component.interfaces;

import org.allaymc.api.eventbus.event.Event;

/**
 * Allay Project 2023/5/6
 *
 * @author daoge_cmd
 */
public interface ComponentManager {
    <E extends Event> E callEvent(E event);
}
