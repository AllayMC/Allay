package cn.allay.api.datastruct.aabbtree;

import java.util.function.Predicate;

@FunctionalInterface
public interface AABBOverlapFilter<T extends Boundable & Identifiable> extends Predicate<T> {

}
