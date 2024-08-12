package org.allaymc.api.eventbus.event.world;

import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.world.World;
import org.joml.Vector3ic;

import lombok.Getter;
import lombok.Setter;

/**
 * Allay Project 2024/08/12
 *
 * @author Dhaiven
 */
@Getter
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
