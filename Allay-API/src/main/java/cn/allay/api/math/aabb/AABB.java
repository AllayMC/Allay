package cn.allay.api.math.aabb;

import cn.allay.api.math.vector.FixedVec3;

/**
 * Allay Project 2023/3/25
 *
 * @author daoge_cmd
 */
public interface AABB extends FixedAABB {
    static AABB of(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        return new ImplAABB(minX, minY, minZ, maxX, maxY, maxZ);
    }

    static AABB of(FixedVec3<Float> min, FixedVec3<Float> max) {
        return new ImplAABB(min.getX(), min.getY(), min.getZ(), max.getX(), max.getY(), max.getZ());
    }

    void setMinX(float minX);

    void setMinY(float minY);

    void setMinZ(float minZ);

    void setMaxX(float maxX);

    void setMaxY(float maxY);

    void setMaxZ(float maxZ);

    default void setAABB(FixedAABB aabb) {
        setMinX(aabb.minX());
        setMinY(aabb.minY());
        setMinZ(aabb.minZ());
        setMaxX(aabb.maxX());
        setMaxY(aabb.maxY());
        setMaxZ(aabb.maxZ());
    }
}
