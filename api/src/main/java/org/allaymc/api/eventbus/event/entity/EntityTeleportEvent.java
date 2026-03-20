package org.allaymc.api.eventbus.event.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.location.Location3dc;

/**
 * Called when an entity is teleported from one location to another.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class EntityTeleportEvent extends EntityEvent implements CancellableEvent {
    protected Location3dc from;
    @Setter
    protected Location3d to;
    @Getter
    protected Reason reason;

    public EntityTeleportEvent(Entity entity, Location3dc from, Location3d to, Reason reason) {
        super(entity);
        this.from = from;
        this.to = to;
        this.reason = reason;
    }

    public boolean isTeleportBetweenWorlds() {
        return from.dimension().getWorld() != to.dimension().getWorld();
    }

    @Getter
    @AllArgsConstructor
    public enum Reason {
        // Unknown reason
        UNKNOWN,
        // Ender pearl
        PROJECTILE,
        // Chorus fruit
        CHORUS_FRUIT,
        // Command
        COMMAND,
        // Nether portal
        NETHER_PORTAL,
        // End portal
        END_PORTAL
    }
}
