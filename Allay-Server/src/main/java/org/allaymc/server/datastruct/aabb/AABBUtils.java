package org.allaymc.server.datastruct.aabb;

import lombok.experimental.UtilityClass;
import org.joml.primitives.AABBf;

/**
 * @author daoge_cmd
 */
@UtilityClass
public final class AABBUtils {
    public static float getWidth(AABBf aabb) {
        return aabb.maxX - aabb.minX;
    }

    public static float getHeight(AABBf aabb) {
        return aabb.maxY - aabb.minY;
    }

    public static float getDepth(AABBf aabb) {
        return aabb.maxZ - aabb.minZ;
    }

    public static float getArea(AABBf aabb) {
        var width = getWidth(aabb);
        var height = getHeight(aabb);
        var depth = getDepth(aabb);
        return 2.0f * (width * height + width * depth + height * depth);
    }
}
