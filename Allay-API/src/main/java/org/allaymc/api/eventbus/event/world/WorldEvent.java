package org.allaymc.api.eventbus.event.world;

import lombok.AllArgsConstructor;
import org.allaymc.api.eventbus.event.Event;
import org.allaymc.api.world.World;

/**
 * Allay Project 2024/8/3
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
public abstract class WorldEvent extends Event {
    protected World world;
}
