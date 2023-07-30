package cn.allay.api.datastruct.aabbtree;

import org.joml.primitives.AABBf;

@FunctionalInterface
public interface AABBTreeHeuristicFunction<T extends Boundable> {
   HeuristicResult getInsertionHeuristic(AABBf left, AABBf right, T object, AABBf objectAABB);

   enum HeuristicResult {
      LEFT,
      RIGHT
   }
}
