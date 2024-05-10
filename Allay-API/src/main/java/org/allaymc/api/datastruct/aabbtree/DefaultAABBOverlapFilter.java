package org.allaymc.api.datastruct.aabbtree;

/**
 * Allay Project 2023/7/30
 *
 * @author daoge_cmd
 */
public class DefaultAABBOverlapFilter<T extends HasAABB & HasLongId> implements AABBOverlapFilter<T> {
    @Override
    public boolean test(T t) {
        return true;
    }
}
