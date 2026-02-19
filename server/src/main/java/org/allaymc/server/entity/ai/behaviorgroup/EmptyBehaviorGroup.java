package org.allaymc.server.entity.ai.behaviorgroup;

import org.allaymc.api.entity.ai.behavior.Behavior;
import org.allaymc.api.entity.ai.behaviorgroup.BehaviorGroup;
import org.allaymc.api.entity.ai.controller.Controller;
import org.allaymc.api.entity.ai.memory.MemoryStorage;
import org.allaymc.api.entity.ai.route.RouteFinder;
import org.allaymc.api.entity.ai.sensor.Sensor;
import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.allaymc.server.entity.ai.memory.SimpleMemoryStorage;

import java.util.Collections;
import java.util.Set;

/**
 * No-op implementation for entities without AI. All methods are empty
 * and return empty sets.
 *
 * @author daoge_cmd
 */
public class EmptyBehaviorGroup implements BehaviorGroup {

    public static final EmptyBehaviorGroup INSTANCE = new EmptyBehaviorGroup();

    protected final MemoryStorage memoryStorage = new SimpleMemoryStorage();

    @Override
    public void collectSensorData(EntityIntelligent entity) {
    }

    @Override
    public void evaluateCoreBehaviors(EntityIntelligent entity) {
    }

    @Override
    public void evaluateBehaviors(EntityIntelligent entity) {
    }

    @Override
    public void tickRunningCoreBehaviors(EntityIntelligent entity) {
    }

    @Override
    public void tickRunningBehaviors(EntityIntelligent entity) {
    }

    @Override
    public void updateRoute(EntityIntelligent entity) {
    }

    @Override
    public void applyController(EntityIntelligent entity) {
    }

    @Override
    public Set<Behavior> getCoreBehaviors() {
        return Collections.emptySet();
    }

    @Override
    public Set<Behavior> getBehaviors() {
        return Collections.emptySet();
    }

    @Override
    public Set<Sensor> getSensors() {
        return Collections.emptySet();
    }

    @Override
    public Set<Controller> getControllers() {
        return Collections.emptySet();
    }

    @Override
    public RouteFinder getRouteFinder() {
        return null;
    }

    @Override
    public MemoryStorage getMemoryStorage() {
        return memoryStorage;
    }

    @Override
    public boolean isRouteUpdateRequired() {
        return false;
    }

    @Override
    public void setRouteUpdateRequired(boolean routeUpdateRequired) {
    }
}
