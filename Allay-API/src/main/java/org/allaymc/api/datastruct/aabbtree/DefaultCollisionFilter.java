package org.allaymc.api.datastruct.aabbtree;

/**
 * Allay Project 2023/7/30
 *
 * @author daoge_cmd
 */
public class DefaultCollisionFilter<T extends HasAABB & HasLongId> implements CollisionFilter<T> {
   @Override
   public boolean test(T t, T t2) {
      return true;
   }
}
