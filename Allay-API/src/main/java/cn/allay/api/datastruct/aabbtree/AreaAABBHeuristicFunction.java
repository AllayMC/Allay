package cn.allay.api.datastruct.aabbtree;

import org.joml.primitives.AABBd;

import static cn.allay.api.datastruct.aabbtree.AABBUtils.getArea;


public class AreaAABBHeuristicFunction<T extends HasAABB> implements AABBTreeHeuristicFunction<T> {
   private final AABBd temp;

   public AreaAABBHeuristicFunction() {
      temp = new AABBd();
   }

   @Override
   public HeuristicResult getInsertionHeuristic(AABBd left, AABBd right, T object, AABBd objectAABB) {
      double diffA = getArea(left.union(objectAABB, temp)) - getArea(left);
      double diffB = getArea(right.union(objectAABB, temp)) - getArea(right);
      return diffA < diffB ? HeuristicResult.LEFT : HeuristicResult.RIGHT;
   }
}
