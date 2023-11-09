package org.allaymc.api.math.location;

import org.allaymc.api.math.position.Position3fc;

/**
 * Allay Project 7/30/2023
 *
 * @author Cool_Loong
 */
public interface Location3fc extends Position3fc {
    double pitch();

    double yaw();

    double headYaw();
}
