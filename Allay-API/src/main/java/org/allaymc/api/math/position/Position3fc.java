package org.allaymc.api.math.position;

import org.allaymc.api.world.Dimension;
import org.joml.Vector3fc;

/**
 * Allay Project 7/30/2023
 *
 * @author Cool_Loong
 */
public interface Position3fc extends Vector3fc {
    Dimension dimension();
}
