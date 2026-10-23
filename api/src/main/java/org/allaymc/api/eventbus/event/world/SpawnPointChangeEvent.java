package org.allaymc.api.eventbus.event.world;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.world.World;
import org.joml.Vector3ic;

/**
 * {@code SpawnPointChangeEvent} carries the current world spawn coordinates
 * and a proposed replacement. Listeners may replace {@code newPos} or cancel
 * the event before the world data setter stores the selected reference; the
 * event does not copy either coordinate object.
 *
 * @author Dhaiven
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class SpawnPointChangeEvent extends WorldEvent implements CancellableEvent {
    /**
     * {@code oldPos} retains the previous spawn reference without copying;
     * listeners must avoid changing that object through mutable aliases.
     */
    protected Vector3ic oldPos;
    /**
     * {@code newPos} retains the proposed spawn reference; accepted dispatch
     * lets the world data setter retain the same object.
     */
    @Setter
    protected Vector3ic newPos;

    /**
     * Creates a spawn proposal retaining both supplied coordinate references.
     * The constructor does not copy either vector, so callers preserve the
     * previous coordinates and keep an accepted replacement stable when the
     * world retains it.
     */
    public SpawnPointChangeEvent(World world, Vector3ic oldPos, Vector3ic newPos) {
        super(world);
        this.oldPos = oldPos;
        this.newPos = newPos;
    }
}
