package org.allaymc.api.eventbus.event.server.world;

import lombok.Getter;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.eventbus.event.WorldEvent;
import org.allaymc.api.world.World;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
@Getter
public class WorldLoadEvent extends WorldEvent implements CancellableEvent {
    public WorldLoadEvent(World world) {
        super(world);
    }
}
