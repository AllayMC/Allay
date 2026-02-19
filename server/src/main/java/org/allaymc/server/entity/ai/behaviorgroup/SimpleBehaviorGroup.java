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
    protected transient Map<Behavior, Integer> coreBehaviorPeriodCounters, behaviorPeriodCounters;
    protected transient Behavior runningCoreBehavior, runningBehavior;

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
        runningCoreBehavior = evaluateBehaviorSet(entity, coreBehaviors, coreBehaviorPeriodCounters, runningCoreBehavior);
    }

    @Override
    public void evaluateBehaviors(EntityIntelligent entity) {
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

    @Override
    public void tickRunningCoreBehaviors(EntityIntelligent entity) {
        runningCoreBehavior = tickRunningBehavior(entity, runningCoreBehavior);
    }

    @Override
    public void tickRunningBehaviors(EntityIntelligent entity) {
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
