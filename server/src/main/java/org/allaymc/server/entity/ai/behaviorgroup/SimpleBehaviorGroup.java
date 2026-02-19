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
import org.allaymc.api.entity.ai.route.RouteFinder;
import org.allaymc.api.entity.ai.sensor.Sensor;
import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.allaymc.api.server.Server;
import org.allaymc.server.entity.ai.memory.SimpleMemoryStorage;
import org.joml.Vector3dc;

import java.util.*;

/**
 * Full AI orchestrator with priority-based behavior selection, period timers,
 * and route management.
 *
 * @author daoge_cmd
 */
@Builder
public class SimpleBehaviorGroup implements BehaviorGroup {

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

    @Getter
    protected MemoryStorage memoryStorage;

    // Period counters for sensors and behaviors
    protected transient Map<Sensor, Integer> sensorPeriodCounters;
    protected transient Map<Behavior, Integer> coreBehaviorPeriodCounters;
    protected transient Map<Behavior, Integer> behaviorPeriodCounters;
    protected transient Behavior runningCoreBehavior;
    protected transient Behavior runningBehavior;

    public static class SimpleBehaviorGroupBuilder {
        // Ensure memoryStorage has a default value
        private MemoryStorage memoryStorage = new SimpleMemoryStorage();
    }

    /**
     * Called when this behavior group is assigned to an entity.
     * Initializes period counters and injects entity reference into memory storage.
     *
     * @param entity the entity this behavior group is assigned to
     */
    public void setEntity(EntityIntelligent entity) {
        if (memoryStorage == null) {
            memoryStorage = new SimpleMemoryStorage();
        }
        memoryStorage.setEntity(entity);
        initPeriodCounters();
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

    @Override
    public void collectSensorData(EntityIntelligent entity) {
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

    @Override
    public void evaluateCoreBehaviors(EntityIntelligent entity) {
        if (coreBehaviorPeriodCounters == null) return;
        // Core behaviors: highest priority evaluated one wins
        Behavior best = null;
        for (var behavior : coreBehaviors) {
            int counter = coreBehaviorPeriodCounters.getOrDefault(behavior, 0) + 1;
            if (counter >= behavior.getPeriod()) {
                if (behavior.evaluate(entity)) {
                    if (best == null || behavior.getPriority() < best.getPriority()) {
                        best = behavior;
                    }
                }
                counter = 0;
            }
            coreBehaviorPeriodCounters.put(behavior, counter);
        }

        if (best != null) {
            if (runningCoreBehavior != null && runningCoreBehavior != best) {
                runningCoreBehavior.onInterrupt(entity);
                runningCoreBehavior.setBehaviorState(BehaviorState.STOP);
            }
            if (runningCoreBehavior != best) {
                best.onStart(entity);
                best.setBehaviorState(BehaviorState.ACTIVE);
            }
            runningCoreBehavior = best;
        }
    }

    @Override
    public void evaluateBehaviors(EntityIntelligent entity) {
        if (behaviorPeriodCounters == null) return;
        Behavior best = null;
        for (var behavior : behaviors) {
            int counter = behaviorPeriodCounters.getOrDefault(behavior, 0) + 1;
            if (counter >= behavior.getPeriod()) {
                if (behavior.evaluate(entity)) {
                    if (best == null || behavior.getPriority() < best.getPriority()) {
                        best = behavior;
                    }
                }
                counter = 0;
            }
            behaviorPeriodCounters.put(behavior, counter);
        }

        if (best != null) {
            if (runningBehavior != null && runningBehavior != best) {
                runningBehavior.onInterrupt(entity);
                runningBehavior.setBehaviorState(BehaviorState.STOP);
            }
            if (runningBehavior != best) {
                best.onStart(entity);
                best.setBehaviorState(BehaviorState.ACTIVE);
            }
            runningBehavior = best;
        }
    }

    @Override
    public void tickRunningCoreBehaviors(EntityIntelligent entity) {
        if (runningCoreBehavior == null) return;
        if (runningCoreBehavior.getBehaviorState() != BehaviorState.ACTIVE) return;

        boolean continueRunning = runningCoreBehavior.execute(entity);
        if (!continueRunning) {
            runningCoreBehavior.onStop(entity);
            runningCoreBehavior.setBehaviorState(BehaviorState.STOP);
            runningCoreBehavior = null;
        }
    }

    @Override
    public void tickRunningBehaviors(EntityIntelligent entity) {
        if (runningBehavior == null) return;
        if (runningBehavior.getBehaviorState() != BehaviorState.ACTIVE) return;

        boolean continueRunning = runningBehavior.execute(entity);
        if (!continueRunning) {
            runningBehavior.onStop(entity);
            runningBehavior.setBehaviorState(BehaviorState.STOP);
            runningBehavior = null;
        }
    }

    @Override
    public void updateRoute(EntityIntelligent entity) {
        if (routeFinder == null) return;

        Vector3dc moveTarget = memoryStorage.get(MemoryTypes.MOVE_TARGET);
        if (moveTarget == null) return;

        if (routeUpdateRequired || routeFinder.hasReached() || !routeFinder.hasNext()) {
            routeUpdateRequired = false;
            // Submit route finding to virtual thread pool
            Server.getInstance().getVirtualThreadPool().submit(() ->
                    routeFinder.search(entity, moveTarget));
        }
    }

    @Override
    public void applyController(EntityIntelligent entity) {
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
