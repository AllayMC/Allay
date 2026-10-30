package org.allaymc.api.entity.ai.behaviorgroup;

import org.allaymc.api.entity.ai.behavior.Behavior;
import org.allaymc.api.entity.ai.controller.Controller;
import org.allaymc.api.entity.ai.memory.MemoryStorage;
import org.allaymc.api.entity.ai.route.RouteFinder;
import org.allaymc.api.entity.ai.sensor.Sensor;

import java.util.Set;

/**
 * {@code BehaviorGroup} exposes the behaviors, sensors, controllers, route
 * finder, and shared memories that drive one entity's AI. Implementations
 * coordinate these pieces during AI ticks and provide a deferred action queue
 * for work requiring the sequential entity tick.
 *
 * @author daoge_cmd
 */
public interface BehaviorGroup {

    /**
     * Returns the configured core behaviors considered independently of
     * ordinary priority selection.
     */
    Set<Behavior> getCoreBehaviors();

    /**
     * Returns the configured ordinary behaviors considered by priority during
     * AI scheduling.
     */
    Set<Behavior> getBehaviors();

    /**
     * Returns the configured sensors that gather observations before behavior
     * evaluation begins.
     */
    Set<Sensor> getSensors();

    /**
     * Returns the configured controllers that apply the movement and
     * orientation memories.
     */
    Set<Controller> getControllers();

    /**
     * Returns the configured route finder, or {@code null} when routing is
     * disabled. Movement controllers may still operate from direction memories
     * supplied directly by behaviors or other callers.
     */
    RouteFinder getRouteFinder();

    /**
     * Returns the shared memory storage used by this group.
     */
    MemoryStorage getMemoryStorage();

    /**
     * Returns whether the group has a pending route refresh request.
     */
    boolean isRouteUpdateRequired();

    /**
     * Sets the pending route refresh request for the group. A request permits
     * recalculation during a later AI tick when a move target and an available
     * route finder exist.
     */
    void setRouteUpdateRequired(boolean routeUpdateRequired);

    /**
     * Queues work for the sequential entity tick after parallel AI processing.
     * Callers use this queue for world mutations requiring sequential execution
     * and must coordinate access with the tick lifecycle. Actions should be
     * non-{@code null}; the default implementation stops its current drain at a
     * {@code null} entry.
     */
    void addSyncedAction(Runnable action);
}
