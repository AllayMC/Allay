package cn.allay.api.datastruct.aabbtree;

import java.util.function.BiPredicate;

/**
 * Allay Project 2023/7/30
 *
 * @author daoge_cmd
 */
@FunctionalInterface
public interface CollisionFilter<T extends HasAABB & HasLongId> extends BiPredicate<T, T> {
}
