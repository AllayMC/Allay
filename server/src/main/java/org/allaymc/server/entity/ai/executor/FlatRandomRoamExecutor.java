package org.allaymc.server.entity.ai.executor;

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

    protected final float speed;
    protected final int maxRoamRange;
    protected final int frequency;
    protected final boolean calNextTargetImmediately;
    protected final int runningTime;
    protected final boolean avoidWater;
    protected final int maxRetryTime;

    protected int tickCounter;
    protected int retryCount;
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
        tickCounter = 0;
        retryCount = 0;
        hasTarget = false;
        entity.setMovementSpeed(speed);
        entity.setPitchEnabled(false);

        if (calNextTargetImmediately) {
            findNewTarget(entity);
        }
    }

    @Override
    public boolean execute(EntityIntelligent entity) {
        tickCounter++;

        if (tickCounter > runningTime) {
            return false;
        }

        if (!hasTarget && tickCounter % frequency == 0) {
            findNewTarget(entity);
        }

        // Check if we've reached the target
        var moveTarget = entity.getMoveTarget();
        if (moveTarget != null) {
            var loc = entity.getLocation();
            double dx = moveTarget.x() - loc.x();
            double dz = moveTarget.z() - loc.z();
            if (dx * dx + dz * dz < 1.0) {
                hasTarget = false;
                EntityControlHelper.removeRouteTarget(entity);
                EntityControlHelper.removeLookTarget(entity);
            }
        }

        return true;
    }

    @Override
    public void onStop(EntityIntelligent entity) {
        EntityControlHelper.removeRouteTarget(entity);
        EntityControlHelper.removeLookTarget(entity);
        entity.setPitchEnabled(true);
    }

    @Override
    public void onInterrupt(EntityIntelligent entity) {
        EntityControlHelper.removeRouteTarget(entity);
        EntityControlHelper.removeLookTarget(entity);
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
            if (blockBelow != null && blockBelow.getBlockType().hasBlockTag(
                    org.allaymc.api.block.data.BlockTags.WATER)) {
                retryCount++;
                return;
            }
        }

        var target = new Vector3d(targetX, targetY, targetZ);
        setRouteTarget(entity, target);
        setLookTarget(entity, target);
        hasTarget = true;
        retryCount = 0;
    }
}
