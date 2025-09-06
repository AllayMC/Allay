package org.allaymc.server.datastruct.aabb;

import org.allaymc.api.world.physics.HasAABB;
import org.joml.primitives.AABBd;

/**
 * @author daoge_cmd
 */
@FunctionalInterface
public interface AABBTreeHeuristicFunction<T extends HasAABB> {
    HeuristicResult getInsertionHeuristic(AABBd left, AABBd right, T object, AABBd objectAABB);

    enum HeuristicResult {
        LEFT,
        RIGHT
    }
}
