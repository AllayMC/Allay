package org.allaymc.api.world.service;

import org.joml.primitives.AABBfc;

/**
 * HasAABB represents an object that has an AABB.
 *
 * @author daoge_cmd
 */
public interface HasAABB {

    /**
     * @return the AABB of the object.
     */
    AABBfc getAABB();

    /**
     * @return the offset AABB of the object.
     */
    AABBfc getOffsetAABB();
}
