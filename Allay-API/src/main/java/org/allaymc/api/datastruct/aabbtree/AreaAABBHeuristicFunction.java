package org.allaymc.api.datastruct.aabbtree;

import org.joml.primitives.AABBf;

import static org.allaymc.api.datastruct.aabbtree.AABBUtils.getArea;

/**
 * Allay Project 2023/7/30
 *
 * @author daoge_cmd
 */
public class AreaAABBHeuristicFunction<T extends HasAABB> implements AABBTreeHeuristicFunction<T> {
    private final AABBf temp = new AABBf();

    @Override
    public HeuristicResult getInsertionHeuristic(AABBf left, AABBf right, T object, AABBf objectAABB) {
        var diffA = getArea(left.union(objectAABB, temp)) - getArea(left);
        var diffB = getArea(right.union(objectAABB, temp)) - getArea(right);
        return diffA < diffB ? HeuristicResult.LEFT : HeuristicResult.RIGHT;
    }
}
