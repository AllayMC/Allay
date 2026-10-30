package org.allaymc.server.datastruct.aabb;

import org.allaymc.api.world.physics.HasAABB;
import org.allaymc.api.world.physics.HasLongId;

/**
 * {@code DefaultCollisionFilter} accepts every candidate collision pair presented by an
 * AABB tree query. It supplies the default predicate when callers request geometric
 * results without additional domain filtering. The tree performs the bounding-box test
 * separately.
 * @author daoge_cmd
 */
public class DefaultCollisionFilter<T extends HasAABB & HasLongId> implements CollisionFilter<T> {
    /**
     * Returns {@code true} for every supplied candidate pair without inspecting its
     * contents.
     */
    @Override
    public boolean test(T t, T t2) {
        return true;
    }
}
