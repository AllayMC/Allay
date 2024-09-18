package org.allaymc.api.eventbus.event.world;

import lombok.AllArgsConstructor;
import org.allaymc.api.eventbus.event.Event;
import org.allaymc.api.world.World;

/**
 * @author daoge_cmd
 */
@AllArgsConstructor
public abstract class WorldEvent extends Event {
    protected World world;
}
