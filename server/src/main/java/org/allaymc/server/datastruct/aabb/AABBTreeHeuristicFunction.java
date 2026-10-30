package org.allaymc.server.datastruct.aabb;

import org.allaymc.api.world.physics.HasAABB;
import org.joml.primitives.AABBd;

/**
 * {@code AABBTreeHeuristicFunction} chooses a child branch while an AABB tree searches for
 * an insertion sibling. The tree supplies both child bounds and the new object's bounds at
 * each branch. Implementations choose one side without modifying the supplied boxes.
 * @author daoge_cmd
 */
@FunctionalInterface
public interface AABBTreeHeuristicFunction<T extends HasAABB> {
    /**
     * Returns the preferred child for inserting the supplied object bounds. The tree owns
     * {@code left}, {@code right}, and {@code objectAABB}; implementations must leave them
     * unchanged so later traversal retains valid branch bounds.
     */
    HeuristicResult getInsertionHeuristic(AABBd left, AABBd right, T object, AABBd objectAABB);

    /**
     * {@code HeuristicResult} identifies which child branch the insertion search follows
     * next.
     */
    enum HeuristicResult {
        /**
         * The existing left child branch.
         */
        LEFT,
        /**
         * The existing right child branch.
         */
        RIGHT
    }
}
