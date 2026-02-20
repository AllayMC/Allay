package org.allaymc.server.entity.ai.behaviorgroup;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import org.allaymc.api.entity.ai.behavior.Behavior;
import org.allaymc.api.entity.ai.behavior.BehaviorState;
import org.allaymc.api.entity.ai.behaviorgroup.BehaviorGroup;
import org.allaymc.api.entity.ai.controller.Controller;
import org.allaymc.api.entity.ai.memory.MemoryStorage;
import org.allaymc.api.entity.ai.memory.MemoryTypes;
import org.allaymc.api.entity.ai.route.Node;
import org.allaymc.api.entity.ai.route.RouteFinder;
import org.allaymc.api.entity.ai.sensor.Sensor;
import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.allaymc.api.server.Server;
import org.allaymc.server.entity.ai.memory.MemoryStorageImpl;
import org.joml.Vector3d;
import org.joml.Vector3dc;

import java.util.*;

/**
 * Full AI orchestrator with priority-based behavior selection, period timers,
 * and route management.
 *
 * @author daoge_cmd
 */
@Builder
public class BehaviorGroupImpl implements BehaviorGroup {

    @Singular
    @Getter
    protected final Set<Behavior> coreBehaviors;

    @Singular
    @Getter
    protected final Set<Behavior> behaviors;

    @Singular
    @Getter
    protected final Set<Sensor> sensors;

    @Singular
    @Getter
    protected final Set<Controller> controllers;

    @Getter
    protected final RouteFinder routeFinder;

    protected boolean routeUpdateRequired;

    // Route state managed locally (RouteFinder is stateless)
    protected transient List<Node> route;
    protected transient int nodeIndex;

    @Builder.Default
    @Getter
    protected MemoryStorage memoryStorage = new MemoryStorageImpl();

    // Period counters for sensors and behaviors
    protected transient Map<Sensor, Integer> sensorPeriodCounters;
    protected transient Map<Behavior, Integer> coreBehaviorPeriodCounters, behaviorPeriodCounters;
    protected transient Behavior runningCoreBehavior, runningBehavior;

    protected transient EntityIntelligent entity;

    /**
     * Called when this behavior group is assigned to an entity.
     * Initializes period counters and injects entity reference into memory storage.
     *
     * @param entity the entity this behavior group is assigned to
     */
    public void setEntity(EntityIntelligent entity) {
        this.entity = entity;
        initPeriodCounters();
    }

    public void tick() {
        if (entity == null) {
            return;
        }

        collectSensorData(entity);
        evaluateCoreBehaviors(entity);
        evaluateBehaviors(entity);
        tickRunningCoreBehaviors(entity);
        tickRunningBehaviors(entity);
        updateRoute(entity);
        applyController(entity);
    }

    protected void initPeriodCounters() {
        sensorPeriodCounters = new HashMap<>();
        for (var sensor : sensors) {
            sensorPeriodCounters.put(sensor, 0);
        }
        coreBehaviorPeriodCounters = new HashMap<>();
        for (var behavior : coreBehaviors) {
            coreBehaviorPeriodCounters.put(behavior, 0);
        }
        behaviorPeriodCounters = new HashMap<>();
        for (var behavior : behaviors) {
            behaviorPeriodCounters.put(behavior, 0);
        }
    }

    protected void collectSensorData(EntityIntelligent entity) {
        if (sensorPeriodCounters == null) return;
        for (var sensor : sensors) {
            int counter = sensorPeriodCounters.getOrDefault(sensor, 0) + 1;
            if (counter >= sensor.getPeriod()) {
                sensor.sense(entity);
                counter = 0;
            }
            sensorPeriodCounters.put(sensor, counter);
        }
    }

    protected void evaluateCoreBehaviors(EntityIntelligent entity) {
        runningCoreBehavior = evaluateBehaviorSet(entity, coreBehaviors, coreBehaviorPeriodCounters, runningCoreBehavior);
    }

    protected void evaluateBehaviors(EntityIntelligent entity) {
        runningBehavior = evaluateBehaviorSet(entity, behaviors, behaviorPeriodCounters, runningBehavior);
    }

    protected Behavior evaluateBehaviorSet(EntityIntelligent entity, Set<Behavior> behaviorSet,
                                           Map<Behavior, Integer> periodCounters, Behavior currentRunning) {
        if (periodCounters == null) return currentRunning;
        Behavior best = null;
        for (var behavior : behaviorSet) {
            int counter = periodCounters.getOrDefault(behavior, 0) + 1;
            if (counter >= behavior.getPeriod()) {
                if (behavior.evaluate(entity)) {
                    if (best == null || behavior.getPriority() < best.getPriority()) {
                        best = behavior;
                    }
                }
                counter = 0;
            }
            periodCounters.put(behavior, counter);
        }

        if (best != null) {
            if (currentRunning != null && currentRunning != best) {
                currentRunning.onInterrupt(entity);
                currentRunning.setBehaviorState(BehaviorState.STOP);
            }
            if (currentRunning != best) {
                best.onStart(entity);
                best.setBehaviorState(BehaviorState.ACTIVE);
            }
            return best;
        }
        return currentRunning;
    }

    protected void tickRunningCoreBehaviors(EntityIntelligent entity) {
        runningCoreBehavior = tickRunningBehavior(entity, runningCoreBehavior);
    }

    protected void tickRunningBehaviors(EntityIntelligent entity) {
        runningBehavior = tickRunningBehavior(entity, runningBehavior);
    }

    protected Behavior tickRunningBehavior(EntityIntelligent entity, Behavior running) {
        if (running == null) return null;
        if (running.getBehaviorState() != BehaviorState.ACTIVE) return running;

        boolean continueRunning = running.execute(entity);
        if (!continueRunning) {
            running.onStop(entity);
            running.setBehaviorState(BehaviorState.STOP);
            return null;
        }
        return running;
    }

    protected void updateRoute(EntityIntelligent entity) {
        if (routeFinder == null) return;

        Vector3dc moveTarget = memoryStorage.get(MemoryTypes.MOVE_TARGET);
        if (moveTarget == null) {
            entity.setMoveDirectionStart(null);
            entity.setMoveDirectionEnd(null);
            return;
        }

        if (routeUpdateRequired || !hasNextNode()) {
            routeUpdateRequired = false;
            // Submit route finding to virtual thread pool
            Server.getInstance().getVirtualThreadPool().submit(() -> {
                this.route = routeFinder.search(entity, moveTarget);
                this.nodeIndex = 0;
            });
        }

        // Auto-advance waypoint when entity is close enough (Paper: followThePath)
        // This runs BEFORE controllers, so WalkController always has a valid ahead-target
        if (!entity.shouldUpdateMoveDirection() && entity.hasMoveDirection()) {
            var end = entity.getMoveDirectionEnd();
            if (end != null) {
                var loc = entity.getLocation();
                double dx = end.x() - loc.x();
                double dz = end.z() - loc.z();
                double horizontalDistSq = dx * dx + dz * dz;
                // Paper: maxDistanceToWaypoint = bbWidth > 0.75 ? bbWidth / 2 : 0.75 - bbWidth / 2
                var aabb = entity.getAABB();
                double bbWidth = aabb.maxX() - aabb.minX();
                double maxDist = bbWidth > 0.75 ? bbWidth / 2.0 : 0.75 - bbWidth / 2.0;
                if (horizontalDistSq < maxDist * maxDist) {
                    entity.setShouldUpdateMoveDirection(true);
                }
            }
        }

        // Consume next direction node when needed
        if (entity.shouldUpdateMoveDirection() || !entity.hasMoveDirection()) {
            if (hasNextNode()) {
                updateMoveDirection(entity);
                entity.setShouldUpdateMoveDirection(false);
            } else if (entity.shouldUpdateMoveDirection()) {
                entity.setMoveDirectionStart(null);
                entity.setMoveDirectionEnd(null);
                entity.setShouldUpdateMoveDirection(false);
            }
        }
    }

    protected boolean hasNextNode() {
        return route != null && nodeIndex < route.size();
    }

    protected Node nextNode() {
        if (!hasNextNode()) return null;
        return route.get(nodeIndex++);
    }

    protected void updateMoveDirection(EntityIntelligent entity) {
        var end = entity.getMoveDirectionEnd();
        var next = nextNode();
        if (next != null) {
            entity.setMoveDirectionStart(end != null ? end : new Vector3d(entity.getLocation().x(), entity.getLocation().y(), entity.getLocation().z()));
            entity.setMoveDirectionEnd(next.getVector());
        }
    }

    protected void applyController(EntityIntelligent entity) {
        for (var controller : controllers) {
            controller.control(entity);
        }
    }

    @Override
    public boolean isRouteUpdateRequired() {
        return routeUpdateRequired;
    }

    @Override
    public void setRouteUpdateRequired(boolean routeUpdateRequired) {
        this.routeUpdateRequired = routeUpdateRequired;
    }
}
