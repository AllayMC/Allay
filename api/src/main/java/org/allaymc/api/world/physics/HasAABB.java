package org.allaymc.api.world.physics;

import org.joml.primitives.AABBdc;

/**
 * HasAABB represents an object that has an AABB.
 *
 * @author daoge_cmd
 */
public interface HasAABB {

    /**
     * @return the AABB of the object
     */
    AABBdc getAABB();

    /**
     * @return the offset AABB of the object
     */
    AABBdc getOffsetAABB();
}
