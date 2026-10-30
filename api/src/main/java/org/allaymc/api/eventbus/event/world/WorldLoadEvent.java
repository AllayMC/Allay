package org.allaymc.api.eventbus.event.world;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.world.World;

/**
 * {@code WorldLoadEvent} represents a prepared world awaiting admission to the
 * loaded-world pool. The pool dispatches it after constructing the dimensions
 * and before registering or starting the world; cancellation leaves the world
 * unregistered and prevents that start operation.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.SERVER)
public class WorldLoadEvent extends WorldEvent implements CancellableEvent {
    /**
     * Creates a pool admission proposal referring to the supplied world.
     */
    public WorldLoadEvent(World world) {
        super(world);
    }
}
