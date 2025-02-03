package org.allaymc.server.datastruct.aabb;

import lombok.experimental.UtilityClass;
import org.joml.primitives.AABBd;

/**
 * @author daoge_cmd
 */
@UtilityClass
public final class AABBUtils {
    public static double getArea(AABBd aabb) {
        var lx = aabb.lengthX();
        var ly = aabb.lengthY();
        var lz = aabb.lengthZ();
        return 2.0 * (lx * ly + lx * lz + ly * lz);
    }
}
