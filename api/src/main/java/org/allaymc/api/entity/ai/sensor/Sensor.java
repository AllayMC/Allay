package org.allaymc.api.entity.ai.sensor;

import org.allaymc.api.entity.interfaces.EntityIntelligent;

/**
 * A sensor collects data from the environment and stores it in memory.
 *
 * @author daoge_cmd
 */
public interface Sensor {

    /**
     * Collect sensor data for the given entity.
     *
     * @param entity the entity to sense for
     */
    void sense(EntityIntelligent entity);

    /**
     * Get the sensing period in ticks.
     *
     * @return the period in ticks (default 1)
     */
    default int getPeriod() {
        return 1;
    }
}
