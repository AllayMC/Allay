package org.allaymc.api.world.physics;

import org.joml.primitives.AABBdc;

/**
 * {@code HasAABB} exposes an object bounding box before and after positional
 * offset. Spatial queries use the offset bounds to compare objects in a common
 * coordinate space, while implementations define how their unoffset bounds are
 * computed.
 *
 * @author daoge_cmd
 */
public interface HasAABB {

    /**
     * Returns the object bounding box before its positional offset is applied.
     */
    AABBdc getAABB();

    /**
     * Returns the bounding box with the object positional offset applied.
     */
    AABBdc getOffsetAABB();
}
