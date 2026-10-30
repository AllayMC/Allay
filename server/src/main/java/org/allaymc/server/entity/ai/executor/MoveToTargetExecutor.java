package org.allaymc.server.entity.ai.executor;

import org.allaymc.api.entity.ai.behavior.BehaviorExecutor;
import org.allaymc.api.entity.ai.memory.MemoryType;
import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.joml.Vector3d;
import org.joml.Vector3dc;

import static org.allaymc.server.entity.ai.executor.EntityControlHelper.removeLookTarget;
import static org.allaymc.server.entity.ai.executor.EntityControlHelper.removeRouteTarget;

/**
 * {@code MoveToTargetExecutor} approaches a position supplied by shared memory
 * while enforcing squared distance bounds. It snapshots accepted target
 * coordinates so later movement can request another route after displacement
 * exceeds one block. Missing target memory or distance outside either bound
 * stops execution.
 *
 * <p>
 * Every accepted target update calls {@code
 * EntityControlHelper.setRouteTarget}, which already requests a route refresh.
 * The {@code updateRouteImmediately} flag repeats that request when enabled but
 * does not suppress refreshes when disabled. The route target retains the
 * supplied position reference, while {@code lastTarget} receives a copy for
 * displacement comparisons. Cleanup clears route and look targets and the
 * snapshot, leaving movement speed as installed by activation.
 *
 * @author daoge_cmd
 */
public class MoveToTargetExecutor implements BehaviorExecutor {

    /**
     * Destination source memory key.
     */
    protected final MemoryType<? extends Vector3dc> memoryType;
    /**
     * Approach movement speed value.
     */
    protected final float speed;
    /**
     * Additional refresh request flag.
     */
    protected final boolean updateRouteImmediately;
    /**
     * Maximum squared destination distance.
     */
    protected final double maxFollowRangeSquared;
    /**
     * Minimum squared destination distance.
     */
    protected final double minFollowRangeSquared;

    /**
     * Copied position from latest target update.
     */
    protected Vector3dc lastTarget;

    /**
     * Creates movement using squared distance bounds of zero through 256
     * inclusive. The supplied flag controls an additional refresh request;
     * accepted target updates already request routing through the helper.
     */
    public MoveToTargetExecutor(MemoryType<? extends Vector3dc> memoryType, float speed,
                                boolean updateRouteImmediately) {
        this(memoryType, speed, updateRouteImmediately, 256.0, 0.0);
    }

    /**
     * Creates movement retaining the supplied squared distance bounds without
     * validation. Target updates retain the source position for routing and
     * copy it separately for later displacement checks and refresh decisions.
     */
    public MoveToTargetExecutor(MemoryType<? extends Vector3dc> memoryType, float speed,
                                boolean updateRouteImmediately,
                                double maxFollowRangeSquared, double minFollowRangeSquared) {
        this.memoryType = memoryType;
        this.speed = speed;
        this.updateRouteImmediately = updateRouteImmediately;
        this.maxFollowRangeSquared = maxFollowRangeSquared;
        this.minFollowRangeSquared = minFollowRangeSquared;
    }

    /**
     * Clears the previous destination snapshot and installs the configured
     * movement speed.
     */
    @Override
    public void onStart(EntityIntelligent entity) {
        lastTarget = null;
        entity.setMovementSpeed(speed);
    }

    /**
     * Updates route and look targets when destination displacement exceeds one
     * block. A missing destination or squared distance outside the inclusive
     * bounds returns {@code false}; otherwise the behavior continues even
     * before any route result is available.
     *
     * <p>
     * The helper requests refresh for every accepted update regardless of
     * {@code updateRouteImmediately}. Enabling that flag repeats the same
     * request. A copied {@code lastTarget} preserves the previous coordinates
     * for displacement checks even when the source memory holds a mutable
     * vector that later changes.
     */
    @Override
    public boolean execute(EntityIntelligent entity) {
        var target = entity.getMemoryStorage().get(memoryType);
        if (target == null) {
            return false;
        }

        var loc = entity.getLocation();
        double distSq = loc.distanceSquared(target);

        if (distSq > maxFollowRangeSquared || distSq < minFollowRangeSquared) {
            return false;
        }

        // A copied snapshot keeps displacement measurable even when the producer mutates
        // its vector in place; changes of one block or less retain the prior route.
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

    /**
     * Clears route and look targets and removes the destination snapshot.
     * Current movement segment endpoints are cleared by the route helper, while
     * the movement speed installed at activation remains unchanged.
     */
    @Override
    public void onStop(EntityIntelligent entity) {
        removeRouteTarget(entity);
        removeLookTarget(entity);
        lastTarget = null;
    }

    /**
     * Uses normal-stop cleanup to release movement targets and the destination
     * snapshot.
     */
    @Override
    public void onInterrupt(EntityIntelligent entity) {
        onStop(entity);
    }
}
