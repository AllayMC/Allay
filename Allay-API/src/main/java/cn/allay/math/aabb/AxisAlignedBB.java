package cn.allay.math.aabb;

import cn.allay.math.vector.Vec3RO;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/25 <br>
 * Allay Project <br>
 */
public interface AxisAlignedBB extends AxisAlignedBBRO {
    static AxisAlignedBB of(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        return new ImplAxisAlignedBB(minX, minY, minZ, maxX, maxY, maxZ);
    }

    static AxisAlignedBB of(Vec3RO<Float> min, Vec3RO<Float> max) {
        return new ImplAxisAlignedBB(min.getX(), min.getY(), min.getZ(), max.getX(), max.getY(), max.getZ());
    }

    void setMinX(float minX);

    void setMinY(float minY);

    void setMinZ(float minZ);

    void setMaxX(float maxX);

    void setMaxY(float maxY);

    void setMaxZ(float maxZ);
}
