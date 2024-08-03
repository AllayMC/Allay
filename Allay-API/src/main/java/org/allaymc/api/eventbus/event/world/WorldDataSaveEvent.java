package org.allaymc.api.eventbus.event.world;

import org.allaymc.api.world.World;

/**
 * Allay Project 2024/08/03
 *
 * @author Dhaiven
 */
public class WorldDataSaveEvent extends WorldEvent {
    public WorldDataSaveEvent(World world) {
        super(world);
    }
}
