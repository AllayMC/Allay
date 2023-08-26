package cn.allay.api.datastruct.aabbtree;

import org.joml.primitives.AABBf;

import static cn.allay.api.datastruct.aabbtree.AABBUtils.getArea;

/**
 * Allay Project 2023/7/30
 *
 * @author daoge_cmd
 */
public class AreaAABBHeuristicFunction<T extends HasAABB> implements AABBTreeHeuristicFunction<T> {
   private final AABBf temp;

   public AreaAABBHeuristicFunction() {
      temp = new AABBf();
   }

   @Override
   public HeuristicResult getInsertionHeuristic(AABBf left, AABBf right, T object, AABBf objectAABB) {
      float diffA = getArea(left.union(objectAABB, temp)) - getArea(left);
      float diffB = getArea(right.union(objectAABB, temp)) - getArea(right);
      return diffA < diffB ? HeuristicResult.LEFT : HeuristicResult.RIGHT;
   }
}
