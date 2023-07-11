package cn.allay.api.math.aabb;

import cn.allay.api.math.vector.Vec3f;

/**
 * Allay Project 2023/3/25
 *
 * @author daoge_cmd
 */
public interface AABB {
    static AABB of(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        return new ImplAABB(minX, minY, minZ, maxX, maxY, maxZ);
    }

    static AABB of(Vec3f min, Vec3f max) {
        return new ImplAABB(min.x(), min.y(), min.z(), max.x(), max.y(), max.z());
    }

    float minX();

    float minY();

    float minZ();

    float maxX();

    float maxY();

    float maxZ();

    default MutableAABB mut() {
        return new ImplMutableAABB(minX(), minY(), minZ(), maxX(), maxY(), maxZ());
    }
}
