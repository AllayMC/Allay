package org.allaymc.api.entity.ai.sensor;

import org.allaymc.api.entity.interfaces.EntityIntelligent;

/**
 * {@code Sensor} gathers observations for an intelligent entity's behavior
 * evaluation. Built-in sensors store results in the entity's shared memories,
 * and the default group schedules sensing before evaluating behaviors so those
 * behaviors can consume the latest eligible observation.
 *
 * @author daoge_cmd
 */
public interface Sensor {

    /**
     * Collects current observations for use by the supplied entity's AI.
     */
    void sense(EntityIntelligent entity);

    /**
     * Returns the interval in group ticks between sensing operations. The
     * default interval is one tick; the default group also treats nonpositive
     * intervals as eligible on every tick.
     */
    default int getPeriod() {
        return 1;
    }
}
