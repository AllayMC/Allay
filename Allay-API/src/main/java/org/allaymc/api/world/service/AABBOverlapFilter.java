package org.allaymc.api.world.service;

import java.util.function.Predicate;

/**
 * AABBOverlapFilter is used to filtering entities that are overlapping with the specified AABB.
 *
 * @author daoge_cmd
 */
@FunctionalInterface
public interface AABBOverlapFilter<T extends HasAABB & HasLongId> extends Predicate<T> {
}
