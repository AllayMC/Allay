package org.allaymc.server.entity.ai.executor;

import org.allaymc.api.entity.ai.behavior.BehaviorExecutor;
import org.allaymc.api.entity.ai.memory.MemoryType;
import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.joml.Vector3d;

/**
 * {@code FollowEntityExecutor} follows a runtime-ID target from memory while
 * maintaining a distance band. The executor stops when the ID cannot resolve or
 * the target exceeds the maximum squared distance, and it keeps looking at the
 * target while inside the minimum distance.
 *
 * <p>
 * Route refreshes compare the target's current position with {@code
 * lastTargetPos}, rather than comparing the follower's position. Entering the
 * minimum distance clears the move target but retains that snapshot. If the
 * follower later moves outside the minimum distance while the target remains
 * near the snapshot, this executor does not reinstall the route until the
 * target displacement exceeds the refresh threshold. Cleanup clears route and
 * look memories without restoring movement speed.
 *
 * @author daoge_cmd
 */
public class FollowEntityExecutor implements BehaviorExecutor {

    /**
     * Target entity runtime-ID key.
     */
    protected final MemoryType<Long> entityIdMemory;
    /**
     * Following movement speed value.
     */
    protected final float speed;
    /**
     * Maximum squared tracking distance.
     */
    protected final double maxRangeSq;
    /**
     * Minimum squared approach distance.
     */
    protected final double minRangeSq;

    /**
     * {@code lastTargetPos} records the last repathed target position. Entering
     * the minimum range clears the route but preserves this snapshot until
     * activation resets it.
     */
    protected Vector3d lastTargetPos;

    /**
     * Creates a follower retaining the target key, speed, and squared distance
     * bounds.
     */
    public FollowEntityExecutor(MemoryType<Long> entityIdMemory, float speed, double maxRangeSq, double minRangeSq) {
        this.entityIdMemory = entityIdMemory;
        this.speed = speed;
        this.maxRangeSq = maxRangeSq;
        this.minRangeSq = minRangeSq;
    }

    /**
     * Resets the previous target snapshot and installs the following movement
     * speed.
     */
    @Override
    public void onStart(EntityIntelligent entity) {
        entity.setMovementSpeed(speed);
        lastTargetPos = null;
    }

    /**
     * Tracks the resolved target within the configured maximum range and
     * updates its eye-height look point. Missing IDs, missing entities, or
     * excessive distance stop execution. Outside the minimum range, only target
     * movement exceeding one block from the last snapshot requests a route.
     *
     * <p>
     * Inside the minimum range, the route is cleared while the snapshot
     * remains. Moving the follower away from a stationary target therefore does
     * not itself restore routing. The continuation result remains {@code true}
     * while the resolved target stays within the maximum range.
     */
    @Override
    public boolean execute(EntityIntelligent entity) {
        var targetId = entity.getMemoryStorage().get(entityIdMemory);
        if (targetId == null) return false;

        var targetEntity = entity.getDimension().getEntityManager().getEntity(targetId);
        if (targetEntity == null) return false;

        var targetLoc = targetEntity.getLocation();
        double distSq = entity.getLocation().distanceSquared(targetLoc);

        if (distSq > maxRangeSq) return false;

        if (distSq > minRangeSq) {
            var targetPos = new Vector3d(targetLoc.x(), targetLoc.y(), targetLoc.z());
            // Refresh only after target displacement exceeds one block from the last snapshot.
            if (lastTargetPos == null || lastTargetPos.distanceSquared(targetPos) > 1.0) {
                EntityControlHelper.setRouteTarget(entity, targetPos);
                lastTargetPos = targetPos;
            }
        } else {
            EntityControlHelper.removeRouteTarget(entity);
        }

        EntityControlHelper.setLookTarget(entity, new Vector3d(
                targetLoc.x(), targetLoc.y() + targetEntity.getEyeHeight(), targetLoc.z()
        ));

        return true;
    }

    /**
     * Clears the route and look targets used by this follower. Movement speed
     * remains installed, and the previous target snapshot is reset only when
     * the next activation begins.
     */
    @Override
    public void onStop(EntityIntelligent entity) {
        EntityControlHelper.removeRouteTarget(entity);
        EntityControlHelper.removeLookTarget(entity);
    }

    /**
     * Clears the route and look targets used by this follower. Movement speed
     * remains installed, and the previous target snapshot is reset only when
     * the next activation begins.
     */
    @Override
    public void onInterrupt(EntityIntelligent entity) {
        onStop(entity);
    }
}
