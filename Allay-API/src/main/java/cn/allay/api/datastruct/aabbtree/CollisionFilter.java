package cn.allay.api.datastruct.aabbtree;

import java.util.function.BiPredicate;

@FunctionalInterface
public interface CollisionFilter<T extends HasAABB & HasLongId> extends BiPredicate<T, T> {
}
