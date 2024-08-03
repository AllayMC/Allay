package org.allaymc.api.eventbus.event.server.player;

import lombok.AllArgsConstructor;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.eventbus.event.Event;

/**
 * Allay Project 2024/8/3
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
public class PlayerUnbanEvent extends Event implements CancellableEvent {
    protected String uuidOrName;
}
