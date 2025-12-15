package org.allaymc.api.eventbus.event.world;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.eventbus.event.Event;
import org.allaymc.api.world.World;

/**
 * The base class for all world events.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public abstract class WorldEvent extends Event {
    protected World world;
}
