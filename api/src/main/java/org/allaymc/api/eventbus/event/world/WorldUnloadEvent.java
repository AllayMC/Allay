package org.allaymc.api.eventbus.event.world;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.world.World;

/**
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.ANY)
public class WorldUnloadEvent extends WorldEvent implements CancellableEvent {
    public WorldUnloadEvent(World world) {
        super(world);
    }
}
