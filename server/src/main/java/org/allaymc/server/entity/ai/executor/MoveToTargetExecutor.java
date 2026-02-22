package org.allaymc.server.entity.ai.executor;

import org.allaymc.api.entity.ai.behavior.BehaviorExecutor;
import org.allaymc.api.entity.ai.memory.MemoryType;
import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.joml.Vector3d;
import org.joml.Vector3dc;

import static org.allaymc.server.entity.ai.executor.EntityControlHelper.removeLookTarget;
import static org.allaymc.server.entity.ai.executor.EntityControlHelper.removeRouteTarget;

/**
 * Moves the entity toward a position stored in a memory type.
 *
 * @author daoge_cmd
 */
public class MoveToTargetExecutor implements BehaviorExecutor {

    protected final MemoryType<? extends Vector3dc> memoryType;
    protected final float speed;
    protected final boolean updateRouteImmediately;
    protected final double maxFollowRangeSquared;
    protected final double minFollowRangeSquared;

    protected Vector3dc lastTarget;

    public MoveToTargetExecutor(MemoryType<? extends Vector3dc> memoryType, float speed,
                                boolean updateRouteImmediately) {
        this(memoryType, speed, updateRouteImmediately, 256.0, 0.0);
    }

    public MoveToTargetExecutor(MemoryType<? extends Vector3dc> memoryType, float speed,
                                boolean updateRouteImmediately,
                                double maxFollowRangeSquared, double minFollowRangeSquared) {
        this.memoryType = memoryType;
        this.speed = speed;
        this.updateRouteImmediately = updateRouteImmediately;
        this.maxFollowRangeSquared = maxFollowRangeSquared;
        this.minFollowRangeSquared = minFollowRangeSquared;
    }

    @Override
    public void onStart(EntityIntelligent entity) {
        lastTarget = null;
        entity.setMovementSpeed(speed);
    }

    @Override
    public boolean execute(EntityIntelligent entity) {
        var target = entity.getMemoryStorage().get(memoryType);
        if (target == null) {
            return false;
        }

        var loc = entity.getLocation();
        double distSq = loc.distanceSquared(target);

        // Check range limits
        if (distSq > maxFollowRangeSquared || distSq < minFollowRangeSquared) {
            return false;
        }

        // Update route if target changed
        if (lastTarget == null || target.distanceSquared(lastTarget) > 1.0) {
            EntityControlHelper.setRouteTarget(entity, target);
            EntityControlHelper.setLookTarget(entity, target);
            if (updateRouteImmediately) {
                entity.getBehaviorGroup().setRouteUpdateRequired(true);
            }
            lastTarget = new Vector3d(target);
        }

        return true;
    }

    @Override
    public void onStop(EntityIntelligent entity) {
        removeRouteTarget(entity);
        removeLookTarget(entity);
        lastTarget = null;
    }

    @Override
    public void onInterrupt(EntityIntelligent entity) {
        onStop(entity);
    }
}
