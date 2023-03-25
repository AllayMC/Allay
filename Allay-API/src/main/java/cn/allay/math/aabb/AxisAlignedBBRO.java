package cn.allay.math.aabb;

import cn.allay.math.vector.Vec3RO;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/25 <br>
 * Allay Project <br>
 */
public interface AxisAlignedBBRO {
    static AxisAlignedBBRO of(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        return new ImplAxisAlignedBBRO(minX, minY, minZ, maxX, maxY, maxZ);
    }

    static AxisAlignedBBRO of(Vec3RO<Float> min, Vec3RO<Float> max) {
        return new ImplAxisAlignedBBRO(min.getX(), min.getY(), min.getZ(), max.getX(), max.getY(), max.getZ());
    }
    float minX();
    float minY();
    float minZ();
    float maxX();
    float maxY();
    float maxZ();
}
