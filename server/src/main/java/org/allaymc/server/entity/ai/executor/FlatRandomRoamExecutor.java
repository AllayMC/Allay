package org.allaymc.server.entity.ai.executor;

import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.entity.ai.behavior.BehaviorExecutor;
import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.joml.Vector3d;

import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.server.entity.ai.executor.EntityControlHelper.setLookTarget;
import static org.allaymc.server.entity.ai.executor.EntityControlHelper.setRouteTarget;

/**
 * Random wandering executor. Picks random XZ targets within range
 * and sets route/look targets.
 *
 * @author daoge_cmd
 */
public class FlatRandomRoamExecutor implements BehaviorExecutor {

    protected static final double TARGET_REACHED_DISTANCE_SQUARED = 1.0;
    protected static final double MIN_PROGRESS_DISTANCE_SQUARED = 0.0625;
    protected static final int MAX_STUCK_TICKS = 40;

    protected final float speed;
    protected final int maxRoamRange;
    protected final int frequency;
    protected final boolean calNextTargetImmediately;
    protected final int runningTime;
    protected final boolean avoidWater;
    protected final int maxRetryTime;

    protected int durationTick;
    protected int targetCalTick;
    protected int retryCount;
    protected int stuckTick;
    protected double bestDistanceSquared;
    protected boolean hadMoveDirection;
    protected boolean hasTarget;

    public FlatRandomRoamExecutor(float speed, int maxRoamRange, int frequency,
                                  boolean calNextTargetImmediately, int runningTime,
                                  boolean avoidWater, int maxRetryTime) {
        this.speed = speed;
        this.maxRoamRange = maxRoamRange;
        this.frequency = frequency;
        this.calNextTargetImmediately = calNextTargetImmediately;
        this.runningTime = runningTime;
        this.avoidWater = avoidWater;
        this.maxRetryTime = maxRetryTime;
    }

    public FlatRandomRoamExecutor(float speed, int maxRoamRange, int frequency) {
        this(speed, maxRoamRange, frequency, true, 100, false, 10);
    }

    @Override
    public void onStart(EntityIntelligent entity) {
        durationTick = 0;
        targetCalTick = 0;
        retryCount = 0;
        stuckTick = 0;
        bestDistanceSquared = Double.MAX_VALUE;
        hadMoveDirection = false;
        hasTarget = false;
        entity.setMovementSpeed(speed);
        entity.setPitchEnabled(false);
        findNewTarget(entity);
    }

    @Override
    public boolean execute(EntityIntelligent entity) {
        durationTick++;
        targetCalTick++;

        // Check if we've reached the target
        var moveTarget = entity.getMoveTarget();
        if (moveTarget != null) {
            var loc = entity.getLocation();
            double dx = moveTarget.x() - loc.x();
            double dz = moveTarget.z() - loc.z();
            double distanceSquared = dx * dx + dz * dz;
            if (entity.hasMoveDirection()) {
                hadMoveDirection = true;
            }
            if (distanceSquared < TARGET_REACHED_DISTANCE_SQUARED) {
                hasTarget = false;
                stuckTick = 0;
                bestDistanceSquared = Double.MAX_VALUE;
                hadMoveDirection = false;
                if (!calNextTargetImmediately) {
                    EntityControlHelper.removeRouteTarget(entity);
                    EntityControlHelper.removeLookTarget(entity);
                }
            } else if (hadMoveDirection && !entity.hasMoveDirection()) {
                abandonTarget(entity);
            } else if (distanceSquared + MIN_PROGRESS_DISTANCE_SQUARED < bestDistanceSquared) {
                bestDistanceSquared = distanceSquared;
                stuckTick = 0;
            } else if (++stuckTick >= MAX_STUCK_TICKS) {
                abandonTarget(entity);
            }
        }

        if (!hasTarget) {
            if (calNextTargetImmediately) {
                findNewTarget(entity);
            } else if (targetCalTick >= frequency) {
                findNewTarget(entity);
            }
        }

        return runningTime <= 0 || durationTick <= runningTime;
    }

    @Override
    public void onStop(EntityIntelligent entity) {
        abandonTarget(entity);
        entity.setPitchEnabled(true);
    }

    @Override
    public void onInterrupt(EntityIntelligent entity) {
        abandonTarget(entity);
        entity.setPitchEnabled(true);
    }

    protected void findNewTarget(EntityIntelligent entity) {
        if (retryCount >= maxRetryTime) {
            return;
        }

        var loc = entity.getLocation();
        var random = ThreadLocalRandom.current();
        double targetX = loc.x() + random.nextInt(-maxRoamRange, maxRoamRange + 1);
        double targetZ = loc.z() + random.nextInt(-maxRoamRange, maxRoamRange + 1);
        double targetY = loc.y();

        if (avoidWater) {
            var dimension = entity.getDimension();
            var blockBelow = dimension.getBlockState(
                    (int) Math.floor(targetX),
                    (int) Math.floor(targetY) - 1,
                    (int) Math.floor(targetZ)
            );
            if (blockBelow != null && blockBelow.getBlockType().hasBlockTag(BlockTags.WATER)) {
                retryCount++;
                return;
            }
        }

        var target = new Vector3d(targetX, targetY, targetZ);
        setRouteTarget(entity, target);
        setLookTarget(entity, target);
        hasTarget = true;
        stuckTick = 0;
        bestDistanceSquared = entity.getLocation().distanceSquared(targetX, targetY, targetZ);
        hadMoveDirection = false;
        targetCalTick = 0;
        retryCount = 0;
    }

    protected void abandonTarget(EntityIntelligent entity) {
        EntityControlHelper.removeRouteTarget(entity);
        EntityControlHelper.removeLookTarget(entity);
        hasTarget = false;
        stuckTick = 0;
        bestDistanceSquared = Double.MAX_VALUE;
        hadMoveDirection = false;
        targetCalTick = frequency;
    }
}
