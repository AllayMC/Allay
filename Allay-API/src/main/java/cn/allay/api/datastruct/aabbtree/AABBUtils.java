package cn.allay.api.datastruct.aabbtree;

import org.joml.primitives.AABBd;

/**
 * Created by pateman.
 */
public final class AABBUtils {

  private AABBUtils() {

  }

  public static double getWidth(AABBd aabb) {
    return aabb.maxX - aabb.minX;
  }

  public static double getHeight(AABBd aabb) {
    return aabb.maxY - aabb.minY;
  }

  public static double getDepth(AABBd aabb) {
    return aabb.maxZ - aabb.minZ;
  }

  public static double getArea(AABBd aabb) {
    final double width = getWidth(aabb);
    final double height = getHeight(aabb);
    final double depth = getDepth(aabb);
    return 2.0 * (width * height + width * depth + height * depth);
  }
}
