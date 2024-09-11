package org.allaymc.api.world.service;

import java.util.function.Predicate;

/**
 * @author daoge_cmd
 */
@FunctionalInterface
public interface AABBOverlapFilter<T extends HasAABB & HasLongId> extends Predicate<T> {
}
