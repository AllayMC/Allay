package cn.allay.api.datastruct.aabbtree;

import java.util.function.BiPredicate;

@FunctionalInterface
public interface CollisionFilter<T extends Boundable & Identifiable> extends BiPredicate<T, T> {
}
