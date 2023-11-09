package org.allaymc.api.math.location;


import org.allaymc.api.math.position.Position3dc;

/**
 * Allay Project 7/30/2023
 *
 * @author Cool_Loong
 */
public interface Location3dc extends Position3dc {
    double pitch();

    double yaw();

    double headYaw();
}
