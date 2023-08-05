package cn.allay.api.datastruct.aabbtree;

public class DefaultAABBOverlapFilter<T extends HasAABB & HasLongId> implements AABBOverlapFilter<T> {
   @Override
   public boolean test(T t) {
      return true;
   }
}
