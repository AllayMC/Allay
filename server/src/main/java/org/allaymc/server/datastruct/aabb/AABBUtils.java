package org.allaymc.server.datastruct.aabb;

import lombok.experimental.UtilityClass;
import org.joml.primitives.AABBd;

/**
 * {@code AABBUtils} calculates the box surface area used by AABB insertion heuristics. The
 * calculation combines the three side-length products and does not mutate the supplied
 * bounds; callers supply valid minimum and maximum coordinates.
 * @author daoge_cmd
 */
@UtilityClass
public final class AABBUtils {
    /**
     * Returns the surface area of the supplied axis-aligned bounding box.
     */
    public static double getArea(AABBd aabb) {
        var lx = aabb.lengthX();
        var ly = aabb.lengthY();
        var lz = aabb.lengthZ();
        return 2.0 * (lx * ly + lx * lz + ly * lz);
    }
}
