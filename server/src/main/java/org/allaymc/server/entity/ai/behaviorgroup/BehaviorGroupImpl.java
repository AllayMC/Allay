package org.allaymc.server.entity.ai.behaviorgroup;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
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

    protected static final int ROUTE_UPDATE_CYCLE = 20;

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

    @Getter
    @Setter
    protected boolean routeUpdateRequired;
    protected volatile boolean routeFinding;

    // Route state managed locally (RouteFinder is stateless)
    protected transient List<Node> route;
    protected transient int nodeIndex;
    protected transient int routeUpdateTick;

    @Builder.Default
    @Getter
    protected MemoryStorage memoryStorage = new MemoryStorageImpl();

    // Period counters for sensors and behaviors
    protected transient Map<Sensor, Integer> sensorPeriodCounters;
    protected transient Map<Behavior, Integer> coreBehaviorPeriodCounters, behaviorPeriodCounters;
    // Running behavior sets (multiple same-priority behaviors can run simultaneously)
    protected transient Set<Behavior> runningCoreBehaviors;
    protected transient Set<Behavior> runningBehaviors;

    protected transient EntityIntelligent entity;

    // Queue for actions that must run sequentially after the parallel AI tick
    protected final Queue<Runnable> syncedActions = new LinkedList<>();

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

    /**
     * Queue an action to be run sequentially after the parallel AI tick completes.
     * Use this for world-modifying operations (e.g., setBlockState) that are
     * not safe to call from parallel threads.
     *
     * @param action the action to queue
     */
    public void addSyncedAction(Runnable action) {
        syncedActions.offer(action);
    }

    /**
     * Process all synced actions. Called from the sequential entity tick.
     */
    public void processSyncedActions() {
        Runnable action;
        while ((action = syncedActions.poll()) != null) {
            action.run();
        }
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
        runningCoreBehaviors = new LinkedHashSet<>();
        runningBehaviors = new LinkedHashSet<>();
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

    /**
     * Core behaviors are independent of priority — any that evaluate to true
     * will start running alongside others. They are never interrupted by priority.
     */
    protected void evaluateCoreBehaviors(EntityIntelligent entity) {
        if (coreBehaviorPeriodCounters == null) return;
        for (var entry : coreBehaviorPeriodCounters.entrySet()) {
            var behavior = entry.getKey();
            // Skip already running core behaviors
            if (runningCoreBehaviors.contains(behavior)) continue;
            int counter = entry.getValue() + 1;
            coreBehaviorPeriodCounters.put(behavior, counter);
            if (counter < behavior.getPeriod()) continue;
            coreBehaviorPeriodCounters.put(behavior, 0);
            if (behavior.evaluate(entity)) {
                behavior.onStart(entity);
                behavior.setBehaviorState(BehaviorState.ACTIVE);
                runningCoreBehaviors.add(behavior);
            }
        }
    }

    /**
     * Normal behaviors use priority-based selection. Higher priority number = higher priority.
     * Only the highest-priority evaluated behaviors are considered.
     * A running behavior is only interrupted by a strictly higher priority.
     */
    protected void evaluateBehaviors(EntityIntelligent entity) {
        if (behaviorPeriodCounters == null) return;
        var evalSucceed = new HashSet<Behavior>();
        int highestPriority = Integer.MIN_VALUE;
        for (var entry : behaviorPeriodCounters.entrySet()) {
            var behavior = entry.getKey();
            // Skip already running behaviors
            if (runningBehaviors.contains(behavior)) continue;
            int counter = entry.getValue() + 1;
            behaviorPeriodCounters.put(behavior, counter);
            if (counter < behavior.getPeriod()) continue;
            behaviorPeriodCounters.put(behavior, 0);
            if (behavior.evaluate(entity)) {
                if (behavior.getPriority() > highestPriority) {
                    evalSucceed.clear();
                    highestPriority = behavior.getPriority();
                } else if (behavior.getPriority() < highestPriority) {
                    continue;
                }
                evalSucceed.add(behavior);
            }
        }
        if (evalSucceed.isEmpty()) return;

        var first = runningBehaviors.isEmpty() ? null : runningBehaviors.iterator().next();
        int runningPriority = first != null ? first.getPriority() : Integer.MIN_VALUE;

        if (highestPriority > runningPriority) {
            // New behaviors have higher priority — interrupt and replace
            interruptRunningBehaviors(entity);
            startBehaviors(entity, evalSucceed);
        } else {
            // Same priority — add alongside
            startBehaviors(entity, evalSucceed);
        }
    }

    protected void interruptRunningBehaviors(EntityIntelligent entity) {
        for (var behavior : runningBehaviors) {
            behavior.onInterrupt(entity);
            behavior.setBehaviorState(BehaviorState.STOP);
        }
        runningBehaviors.clear();
    }

    protected void startBehaviors(EntityIntelligent entity, Set<Behavior> toStart) {
        for (var behavior : toStart) {
            behavior.onStart(entity);
            behavior.setBehaviorState(BehaviorState.ACTIVE);
            runningBehaviors.add(behavior);
        }
    }

    protected void tickRunningCoreBehaviors(EntityIntelligent entity) {
        if (runningCoreBehaviors == null) return;
        var it = runningCoreBehaviors.iterator();
        while (it.hasNext()) {
            var behavior = it.next();
            if (!behavior.execute(entity)) {
                behavior.onStop(entity);
                behavior.setBehaviorState(BehaviorState.STOP);
                it.remove();
            }
        }
    }

    protected void tickRunningBehaviors(EntityIntelligent entity) {
        if (runningBehaviors == null) return;
        var it = runningBehaviors.iterator();
        while (it.hasNext()) {
            var behavior = it.next();
            if (!behavior.execute(entity)) {
                behavior.onStop(entity);
                behavior.setBehaviorState(BehaviorState.STOP);
                it.remove();
            }
        }
    }

    protected void updateRoute(EntityIntelligent entity) {
        if (routeFinder == null) return;

        Vector3dc moveTarget = memoryStorage.get(MemoryTypes.MOVE_TARGET);
        if (moveTarget == null) {
            entity.setMoveDirectionStart(null);
            entity.setMoveDirectionEnd(null);
            return;
        }

        // Periodically force route recalculation so stale paths
        // (caused by block changes along the route) get refreshed.
        if (hasNextNode()) {
            routeUpdateTick++;
            if (routeUpdateTick >= ROUTE_UPDATE_CYCLE) {
                routeUpdateTick = 0;
                routeUpdateRequired = true;
            }
        }

        if ((routeUpdateRequired || !hasNextNode()) && !routeFinding) {
            routeUpdateRequired = false;
            routeFinding = true;
            // Submit route finding to virtual thread pool
            Server.getInstance().getVirtualThreadPool().submit(() -> {
                try {
                    this.route = routeFinder.search(entity, moveTarget);
                    this.nodeIndex = 0;
                } finally {
                    routeFinding = false;
                }
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
}
