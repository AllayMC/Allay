package org.allaymc.api.eventbus.event.world;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.world.World;

/**
 * Called when a world is about to be unloaded.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.SERVER)
public class WorldUnloadEvent extends WorldEvent implements CancellableEvent {
    public WorldUnloadEvent(World world) {
        super(world);
    }
}
