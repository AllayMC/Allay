package org.allaymc.server.datastruct.aabb;

import org.allaymc.api.world.physics.HasAABB;
import org.joml.primitives.AABBd;

import static org.allaymc.server.datastruct.aabb.AABBUtils.getArea;

/**
 * @author daoge_cmd
 */
public class AreaAABBHeuristicFunction<T extends HasAABB> implements AABBTreeHeuristicFunction<T> {
    private final AABBd temp = new AABBd();

    @Override
    public HeuristicResult getInsertionHeuristic(AABBd left, AABBd right, T object, AABBd objectAABB) {
        var diffA = getArea(left.union(objectAABB, temp)) - getArea(left);
        var diffB = getArea(right.union(objectAABB, temp)) - getArea(right);
        return diffA < diffB ? HeuristicResult.LEFT : HeuristicResult.RIGHT;
    }
}
