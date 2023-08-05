package cn.allay.api.datastruct.aabbtree;

import org.joml.primitives.AABBd;

@FunctionalInterface
public interface AABBTreeHeuristicFunction<T extends HasAABB> {
   HeuristicResult getInsertionHeuristic(AABBd left, AABBd right, T object, AABBd objectAABB);

   enum HeuristicResult {
      LEFT,
      RIGHT
   }
}
