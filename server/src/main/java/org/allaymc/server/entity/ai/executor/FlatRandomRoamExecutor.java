package org.allaymc.server.entity.ai.executor;

import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.entity.ai.behavior.BehaviorExecutor;
import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.joml.Vector3d;

import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.server.entity.ai.executor.EntityControlHelper.setLookTarget;
import static org.allaymc.server.entity.ai.executor.EntityControlHelper.setRouteTarget;

/**
 * {@code FlatRandomRoamExecutor} maintains random horizontal destinations for a
 * timed or unlimited wandering behavior. Each destination uses the entity's
 * current height and an integer XZ displacement; optional water avoidance
 * inspects the block beneath that candidate rather than validating the entire
 * path.
 *
 * <p>
 * Execution tracks arrival, previous movement-direction availability, and
 * improvement in squared distance. A vanished direction after movement begins
 * or prolonged lack of progress abandons the destination and permits another
 * attempt. Candidate retries count rejected water positions and reset on
 * success, while the execution lifetime advances independently. Activation
 * disables pitch and installs movement speed; cleanup clears route and look
 * memories and enables pitch without restoring the previous pitch flag or
 * speed.
 *
 * @author daoge_cmd
 */
public class FlatRandomRoamExecutor implements BehaviorExecutor {

    /**
     * {@code TARGET_REACHED_DISTANCE_SQUARED} defines the horizontal arrival
     * radius for random destinations, allowing a new target before exact
     * coordinate equality is reached.
     */
    protected static final double TARGET_REACHED_DISTANCE_SQUARED = 1.0;
    /**
     * {@code MIN_PROGRESS_DISTANCE_SQUARED} sets the squared-distance
     * improvement needed to reset the stuck timer, filtering smaller changes in
     * distance from progress accounting.
     */
    protected static final double MIN_PROGRESS_DISTANCE_SQUARED = 0.0625;
    /**
     * {@code MAX_STUCK_TICKS} limits consecutive executions without sufficient
     * progress, allowing the executor to abandon a destination that no longer
     * produces useful movement.
     */
    protected static final int MAX_STUCK_TICKS = 40;

    /**
     * Roaming movement speed value.
     */
    protected final float speed;
    /**
     * Maximum random XZ block displacement.
     */
    protected final int maxRoamRange;
    /**
     * Target attempt interval in ticks.
     */
    protected final int frequency;
    /**
     * Immediate target replacement flag.
     */
    protected final boolean calNextTargetImmediately;
    /**
     * Execution lifetime in ticks.
     */
    protected final int runningTime;
    /**
     * Candidate water rejection flag.
     */
    protected final boolean avoidWater;
    /**
     * Consecutive water rejection limit.
     */
    protected final int maxRetryTime;

    // durationTick limits the activation lifetime, while targetCalTick schedules
    // destination attempts. retryCount tracks rejected water candidates independently
    // of stuckTick, which uses bestDistanceSquared to track movement progress.
    /**
     * Execution ticks since activation.
     */
    protected int durationTick;
    /**
     * Ticks since last accepted target.
     */
    protected int targetCalTick;
    /**
     * Consecutive rejected water candidates.
     */
    protected int retryCount;
    /**
     * Executions without sufficient distance progress.
     */
    protected int stuckTick;
    /**
     * Best remaining squared target distance.
     */
    protected double bestDistanceSquared;
    /**
     * Movement direction previously observed flag.
     */
    protected boolean hadMoveDirection;
    /**
     * Accepted target tracking flag.
     */
    protected boolean hasTarget;

    /**
     * Creates a roaming executor retaining the supplied scheduling and
     * candidate settings. A nonpositive {@code runningTime} permits unlimited
     * execution, while nonpositive {@code maxRetryTime} prevents destination
     * attempts; random range validity is checked only when sampling.
     */
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

    /**
     * Creates roaming with immediate destination replacement and a hundred-tick
     * lifetime. Water avoidance is disabled, the water retry limit is ten, and
     * the supplied speed, range, and frequency remain unchanged.
     */
    public FlatRandomRoamExecutor(float speed, int maxRoamRange, int frequency) {
        this(speed, maxRoamRange, frequency, true, 100, false, 10);
    }

    /**
     * Resets progress and retry tracking, installs roaming speed, disables
     * pitch, and attempts a destination. Candidate rejection can leave the
     * executor without a target until later execution retries it.
     */
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

    /**
     * Updates roaming progress and chooses another destination when needed.
     * Arrival clears local target tracking; route loss after a direction was
     * observed and insufficient distance progress instead abandon route and
     * look targets. The progress threshold compares changes in squared
     * distance.
     *
     * <p>
     * Immediate replacement attempts a candidate whenever no target is tracked;
     * delayed replacement waits for {@code frequency}. Water rejections
     * preserve their retry count across attempts until success. The method
     * continues indefinitely for a nonpositive lifetime, or through executions
     * whose incremented timer is no greater than the configured lifetime.
     */
    @Override
    public boolean execute(EntityIntelligent entity) {
        durationTick++;
        targetCalTick++;

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
                // Losing a direction after movement began signals route exhaustion
                // before arrival. Abandon it so later attempts can seek another target.
                abandonTarget(entity);
            } else if (distanceSquared + MIN_PROGRESS_DISTANCE_SQUARED < bestDistanceSquared) {
                bestDistanceSquared = distanceSquared;
                stuckTick = 0;
            } else if (++stuckTick >= MAX_STUCK_TICKS) {
                // Only sufficient distance improvements reset stuckTick. Repeated small
                // or absent changes eventually abandon this destination and permit another attempt.
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

    /**
     * Abandons the roaming destination and enables pitch for later behaviors.
     * Cleanup clears movement and look targets but does not restore the
     * previous pitch flag or movement speed value.
     */
    @Override
    public void onStop(EntityIntelligent entity) {
        abandonTarget(entity);
        entity.setPitchEnabled(true);
    }

    /**
     * Abandons the roaming destination and enables pitch for later behaviors.
     * Cleanup clears movement and look targets but does not restore the
     * previous pitch flag or movement speed value.
     */
    @Override
    public void onInterrupt(EntityIntelligent entity) {
        abandonTarget(entity);
        entity.setPitchEnabled(true);
    }

    /**
     * Attempts one random horizontal destination unless the water rejection
     * limit has been reached. A rejected water candidate increments the retry
     * count without resetting the attempt timer; success installs both targets
     * and resets progress and retries.
     */
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
                // Reject only this candidate and retain the attempt timer, allowing a
                // later execution to sample again until consecutive water rejections reach the limit.
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

    /**
     * Clears route and look targets and resets local progress tracking. The
     * attempt timer becomes {@code frequency}, allowing delayed replacement to
     * try again during the next eligible execution rather than waiting another
     * full interval.
     */
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
