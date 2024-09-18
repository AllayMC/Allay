package org.allaymc.api.math.position;

import org.allaymc.api.world.Dimension;
import org.cloudburstmc.math.vector.Vector3i;
import org.joml.Vector3ic;

/**
 * @author Cool_Loong
 */
public interface Position3ic extends Vector3ic {
    Dimension dimension();

    default Vector3i toNetwork() {
        return Vector3i.from(this.x(), this.y(), this.z());
    }
}
