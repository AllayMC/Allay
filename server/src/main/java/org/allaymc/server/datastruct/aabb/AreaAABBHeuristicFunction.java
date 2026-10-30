package org.allaymc.server.datastruct.aabb;

import org.allaymc.api.world.physics.HasAABB;
import org.joml.primitives.AABBd;

import static org.allaymc.server.datastruct.aabb.AABBUtils.getArea;

/**
 * {@code AreaAABBHeuristicFunction} chooses the insertion branch with the smaller increase
 * in bounding-box surface area. Each candidate area is compared with that child's existing
 * area, so the choice depends on growth rather than final box size alone. Equal growth
 * chooses the right branch.
 *
 * <p>
 * The calculation writes each candidate union into a reusable scratch box while leaving
 * the supplied branch boxes unchanged. Both growth values must be measured before choosing
 * the branch; comparing only the existing child areas would ignore the new object's
 * position and dimensions. The shared scratch storage makes concurrent or reentrant use of
 * one heuristic instance unsupported without caller coordination.
 * @author daoge_cmd
 */
public class AreaAABBHeuristicFunction<T extends HasAABB> implements AABBTreeHeuristicFunction<T> {
    /**
     * {@code temp} holds one candidate union at a time during branch selection. Calls on
     * this heuristic instance must not overlap because they reuse the same mutable box.
     */
    private final AABBd temp = new AABBd();

    /**
     * Returns the child whose union with {@code objectAABB} adds less surface area. The
     * supplied boxes remain unchanged, and {@code object} itself does not participate in
     * this calculation. Equal increases select {@code RIGHT}.
     *
     * <p>
     * Each union is measured against its own original child area, preserving the
     * distinction between an already large child and a small child that would grow
     * substantially. The method reuses {@code temp} for both candidates, so callers must
     * serialize calls on this heuristic instance.
     */
    @Override
    public HeuristicResult getInsertionHeuristic(AABBd left, AABBd right, T object, AABBd objectAABB) {
        var diffA = getArea(left.union(objectAABB, temp)) - getArea(left);
        var diffB = getArea(right.union(objectAABB, temp)) - getArea(right);
        return diffA < diffB ? HeuristicResult.LEFT : HeuristicResult.RIGHT;
    }
}
