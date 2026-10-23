package org.allaymc.server.datastruct.aabb;

import org.allaymc.api.world.physics.HasAABB;
import org.allaymc.api.world.physics.HasLongId;

import java.util.function.BiPredicate;

/**
 * {@code CollisionFilter} decides whether an overlapping pair of identified objects is
 * included in collision results. The tree applies this predicate after cached boxes
 * overlap. Implementations can reject pairs for domain rules without changing the tree's
 * geometric traversal.
 * @author daoge_cmd
 */
@FunctionalInterface
public interface CollisionFilter<T extends HasAABB & HasLongId> extends BiPredicate<T, T> {
}
