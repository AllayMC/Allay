package org.allaymc.api.math.position;

import org.allaymc.api.world.Dimension;
import org.joml.Vector3dc;

/**
 * @author Cool_Loong
 */
public interface Position3dc extends Vector3dc {
    Dimension dimension();
}
