package org.allaymc.api.math.location;

import org.allaymc.api.math.position.Position3ic;

/**
 * {@code Location3ic} exposes integer coordinates and an associated dimension together
 * with viewing angles in degrees. This interface supplies a read-only access surface, but
 * implementations may remain mutable; retaining the interface does not freeze coordinates
 * or orientation or keep a weakly referenced dimension alive.
 * @author Cool_Loong
 */
public interface Location3ic extends Position3ic {
    /**
     * Returns the {@code pitch} viewing angle measured in degrees.
     */
    double pitch();

    /**
     * Returns the {@code yaw} viewing angle measured in degrees.
     */
    double yaw();

}
