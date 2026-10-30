package org.allaymc.api.world.physics;

import java.util.function.Predicate;

/**
 * {@code AABBOverlapFilter} selects objects that satisfy an axis-aligned
 * bounding-box query. Its predicate runs on candidates found by the spatial
 * index, and the combined bounds and long-identifier contracts let that index
 * locate and identify each object.
 *
 * @author daoge_cmd
 */
@FunctionalInterface
public interface AABBOverlapFilter<T extends HasAABB & HasLongId> extends Predicate<T> {
}
