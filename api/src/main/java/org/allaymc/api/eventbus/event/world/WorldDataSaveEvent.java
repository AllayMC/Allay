package org.allaymc.api.eventbus.event.world;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.world.World;

/**
 * Called when the world data is saved.
 *
 * @author Dhaiven
 */
@CallerThread(ThreadType.WORLD)
public class WorldDataSaveEvent extends WorldEvent {
    public WorldDataSaveEvent(World world) {
        super(world);
    }
}
