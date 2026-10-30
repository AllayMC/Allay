package org.allaymc.server.entity.ai.executor;

import org.allaymc.api.entity.ai.memory.MemoryTypes;
import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.joml.Vector3dc;

/**
 * {@code EntityControlHelper} updates movement and look memories used by
 * behavior executors. Setting a route target also requests recalculation, while
 * removing one clears the current movement segment so controllers stop
 * following that segment even before another route search finishes.
 *
 * @author daoge_cmd
 */
public final class EntityControlHelper {

    /**
     * Prevents instantiation of the shared entity target control helper holder.
     */
    private EntityControlHelper() {
    }

    /**
     * Stores the supplied move target and requests a route refresh. The entity
     * retains the target reference through memory storage, so later mutations
     * can affect routing without another helper call.
     */
    public static void setRouteTarget(EntityIntelligent entity, Vector3dc target) {
        entity.setMoveTarget(target);
        entity.getBehaviorGroup().setRouteUpdateRequired(true);
    }

    /**
     * Stores the supplied look target for orientation controllers to consume.
     * The entity retains the target reference through memory storage; callers
     * must account for later changes to mutable vector instances.
     */
    public static void setLookTarget(EntityIntelligent entity, Vector3dc target) {
        entity.setLookTarget(target);
    }

    /**
     * Clears the move target and both current movement segment endpoints. This
     * helper does not cancel an outstanding asynchronous search or erase the
     * behavior group's cached route nodes.
     */
    public static void removeRouteTarget(EntityIntelligent entity) {
        entity.getMemoryStorage().clear(MemoryTypes.MOVE_TARGET);
        entity.setMoveDirectionStart(null);
        entity.setMoveDirectionEnd(null);
    }

    /**
     * Clears the look target memory consumed by entity orientation controllers.
     */
    public static void removeLookTarget(EntityIntelligent entity) {
        entity.getMemoryStorage().clear(MemoryTypes.LOOK_TARGET);
    }
}
