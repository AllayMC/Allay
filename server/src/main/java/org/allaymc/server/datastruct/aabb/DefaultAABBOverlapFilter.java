package org.allaymc.server.datastruct.aabb;

import org.allaymc.api.world.physics.AABBOverlapFilter;
import org.allaymc.api.world.physics.HasAABB;
import org.allaymc.api.world.physics.HasLongId;

/**
 * {@code DefaultAABBOverlapFilter} accepts every candidate object presented by an AABB
 * tree query. It supplies the default predicate when callers request geometric results
 * without additional domain filtering. The tree performs the bounding-box test separately.
 * @author daoge_cmd
 */
public class DefaultAABBOverlapFilter<T extends HasAABB & HasLongId> implements AABBOverlapFilter<T> {
    /**
     * Returns {@code true} for every supplied candidate object without inspecting its
     * contents.
     */
    @Override
    public boolean test(T t) {
        return true;
    }
}
