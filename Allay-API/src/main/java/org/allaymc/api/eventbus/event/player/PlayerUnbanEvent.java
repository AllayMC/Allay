package org.allaymc.api.eventbus.event.player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.eventbus.event.Event;

/**
 * Allay Project 2024/8/3
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
@Getter
public class PlayerUnbanEvent extends Event implements CancellableEvent {
    protected String uuidOrName;
}
