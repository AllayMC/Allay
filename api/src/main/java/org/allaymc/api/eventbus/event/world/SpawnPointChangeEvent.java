package org.allaymc.api.eventbus.event.world;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.world.World;
import org.joml.Vector3ic;

/**
 * @author Dhaiven
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class SpawnPointChangeEvent extends WorldEvent implements CancellableEvent {
    protected Vector3ic oldPos;
    @Setter
    protected Vector3ic newPos;

    public SpawnPointChangeEvent(World world, Vector3ic oldPos, Vector3ic newPos) {
        super(world);
        this.oldPos = oldPos;
        this.newPos = newPos;
    }
}
