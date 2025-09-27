package org.allaymc.api.math.position;

import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

/**
 * @author Cool_Loong
 */
public interface Position3ic extends Vector3ic {
    Dimension dimension();
}
