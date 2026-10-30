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
 * {@code EntityTeleportEvent} carries a source location, editable destination,
 * and reason for a spawned entity teleport. The teleport path copies its
 * initial target before dispatch, then uses the listener-adjusted {@code to}
 * reference; cancellation prevents starting the accepted teleport's location
 * and membership changes.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class EntityTeleportEvent extends EntityEvent implements CancellableEvent {
    /**
     * {@code from} retains the source location reference supplied to the
     * event. The getter exposes the same object; listeners must preserve the
     * source and keep any accepted destination usable for the location update.
     */
    protected Location3dc from;
    /**
     * {@code to} retains the editable proposed destination reference supplied
     * to the event. The getter exposes the same object; listeners must
     * preserve the source and keep any accepted destination usable for the
     * location update.
     */
    @Setter
    protected Location3d to;
    /**
     * Reason supplied for the proposed entity teleport operation.
     */
    @Getter
    protected Reason reason;

    /**
     * Creates a teleport proposal retaining its source, destination, and
     * entity references. The constructor does not copy locations; the teleport
     * caller supplies its own destination copy before dispatching this event.
     */
    public EntityTeleportEvent(Entity entity, Location3dc from, Location3d to, Reason reason) {
        super(entity);
        this.from = from;
        this.to = to;
        this.reason = reason;
    }

    /**
     * Returns whether the source and current destination belong to different
     * world instances. The comparison uses world reference identity, and both
     * location dimensions must remain usable when listeners call this helper.
     */
    public boolean isTeleportBetweenWorlds() {
        return from.dimension().getWorld() != to.dimension().getWorld();
    }

    /**
     * {@code Reason} identifies the gameplay source of an entity teleport
     * request.
     */
    @Getter
    @AllArgsConstructor
    public enum Reason {
        /**
         * Unspecified source of the requested entity teleport.
         */
        UNKNOWN,
        /**
         * Projectile impact such as an ender pearl teleport.
         */
        PROJECTILE,
        /**
         * Chorus fruit consumption causing the entity teleport.
         */
        CHORUS_FRUIT,
        /**
         * Command execution requesting the entity teleport operation.
         */
        COMMAND,
        /**
         * Nether portal travel requesting the entity teleport.
         */
        NETHER_PORTAL,
        /**
         * End portal travel requesting the entity teleport.
         */
        END_PORTAL
    }
}
