package org.allaymc.api.datastruct.aabbtree;

import java.util.function.Predicate;

/**
 * Allay Project 2023/7/30
 *
 * @author daoge_cmd
 */
@FunctionalInterface
public interface AABBOverlapFilter<T extends HasAABB & HasLongId> extends Predicate<T> {
}
