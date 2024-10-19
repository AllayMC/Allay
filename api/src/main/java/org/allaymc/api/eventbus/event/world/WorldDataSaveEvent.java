package org.allaymc.api.eventbus.event.world;

import org.allaymc.api.world.World;

/**
 * @author Dhaiven
 */
public class WorldDataSaveEvent extends WorldEvent {
    public WorldDataSaveEvent(World world) {
        super(world);
    }
}
