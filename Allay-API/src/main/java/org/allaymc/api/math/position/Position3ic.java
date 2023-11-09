package org.allaymc.api.math.position;

import org.allaymc.api.world.World;
import org.joml.Vector3ic;

/**
 * Allay Project 7/30/2023
 *
 * @author Cool_Loong
 */
public interface Position3ic extends Vector3ic {
    World world();
}
