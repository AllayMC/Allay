package cn.allay.api.math.aabb;

import cn.allay.api.math.vector.FixedVec3;

/**
 * Allay Project 2023/3/25
 *
 * @author daoge_cmd
 */
public interface FixedAABB {
    static FixedAABB of(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        return new ImplFixedAABB(minX, minY, minZ, maxX, maxY, maxZ);
    }

    static FixedAABB of(FixedVec3<Float> min, FixedVec3<Float> max) {
        return new ImplFixedAABB(min.getX(), min.getY(), min.getZ(), max.getX(), max.getY(), max.getZ());
    }

    float minX();

    float minY();

    float minZ();

    float maxX();

    float maxY();

    float maxZ();
}
