package org.allaymc.api.entity.ai.behaviorgroup;

import org.allaymc.api.entity.ai.behavior.Behavior;
import org.allaymc.api.entity.ai.controller.Controller;
import org.allaymc.api.entity.ai.memory.MemoryStorage;
import org.allaymc.api.entity.ai.route.RouteFinder;
import org.allaymc.api.entity.ai.sensor.Sensor;
import org.allaymc.api.entity.interfaces.EntityIntelligent;

import java.util.Set;

/**
 * Orchestrates the AI tick pipeline: sensors, evaluators, executors,
 * route finding, and controllers.
 *
 * @author daoge_cmd
 */
public interface BehaviorGroup {

    /**
     * Collect sensor data for the given entity.
     *
     * @param entity the entity
     */
    void collectSensorData(EntityIntelligent entity);

    /**
     * Evaluate core behaviors (always-running behaviors).
     *
     * @param entity the entity
     */
    void evaluateCoreBehaviors(EntityIntelligent entity);

    /**
     * Evaluate non-core behaviors.
     *
     * @param entity the entity
     */
    void evaluateBehaviors(EntityIntelligent entity);

    /**
     * Tick running core behaviors.
     *
     * @param entity the entity
     */
    void tickRunningCoreBehaviors(EntityIntelligent entity);

    /**
     * Tick running non-core behaviors.
     *
     * @param entity the entity
     */
    void tickRunningBehaviors(EntityIntelligent entity);

    /**
     * Update the route using the route finder.
     *
     * @param entity the entity
     */
    void updateRoute(EntityIntelligent entity);

    /**
     * Apply all controllers to the entity.
     *
     * @param entity the entity
     */
    void applyController(EntityIntelligent entity);

    /**
     * Get all core behaviors.
     *
     * @return the set of core behaviors
     */
    Set<Behavior> getCoreBehaviors();

    /**
     * Get all non-core behaviors.
     *
     * @return the set of behaviors
     */
    Set<Behavior> getBehaviors();

    /**
     * Get all sensors.
     *
     * @return the set of sensors
     */
    Set<Sensor> getSensors();

    /**
     * Get all controllers.
     *
     * @return the set of controllers
     */
    Set<Controller> getControllers();

    /**
     * Get the route finder.
     *
     * @return the route finder, or {@code null} if none
     */
    RouteFinder getRouteFinder();

    /**
     * Get the memory storage.
     *
     * @return the memory storage
     */
    MemoryStorage getMemoryStorage();

    /**
     * Check if the route needs to be updated on the next tick.
     *
     * @return {@code true} if a route update is needed
     */
    boolean isRouteUpdateRequired();

    /**
     * Set whether the route needs to be updated on the next tick.
     *
     * @param routeUpdateRequired whether a route update is needed
     */
    void setRouteUpdateRequired(boolean routeUpdateRequired);
}
