package cn.allay.api.datastruct.aabbtree;

import org.joml.primitives.AABBf;

/**
 * Allay Project 2023/7/30
 *
 * @author daoge_cmd
 */
@FunctionalInterface
public interface AABBTreeHeuristicFunction<T extends HasAABB> {
   HeuristicResult getInsertionHeuristic(AABBf left, AABBf right, T object, AABBf objectAABB);

   enum HeuristicResult {
      LEFT,
      RIGHT
   }
}
