package cn.allay.api.datastruct.aabbtree;

import java.util.function.Predicate;

@FunctionalInterface
public interface AABBOverlapFilter<T extends HasAABB & HasLongId> extends Predicate<T> {

}
