package org.allaymc.api.math.location;

import org.allaymc.api.math.position.Position3ic;

/**
 * Allay Project 7/30/2023
 *
 * @author Cool_Loong
 */
public interface Location3ic extends Position3ic {
    double pitch();

    double yaw();

    double headYaw();
}
