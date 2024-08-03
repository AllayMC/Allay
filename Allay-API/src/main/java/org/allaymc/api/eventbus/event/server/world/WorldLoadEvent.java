package org.allaymc.api.eventbus.event.server.world;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.eventbus.event.Event;
import org.allaymc.api.world.World;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class WorldLoadEvent extends Event implements CancellableEvent {
    protected World world;
}
