package org.allaymc.api.world.service;

import org.joml.primitives.AABBf;

/**
 * HasAABB represents an object that has an AABB.
 *
 * @author daoge_cmd
 */
public interface HasAABB {
    /**
     * Copy the offset AABB to the specified dest AABB.
     *
     * @return the copied AABB
     */
    AABBf copyOffsetAABBTo(AABBf dest);
}
