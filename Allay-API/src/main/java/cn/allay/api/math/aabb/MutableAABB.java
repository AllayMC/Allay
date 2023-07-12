package cn.allay.api.math.aabb;

/**
 * Allay Project 2023/3/25
 *
 * @author daoge_cmd
 */
public interface MutableAABB extends AABB {
    void setMinX(float minX);

    void setMinY(float minY);

    void setMinZ(float minZ);

    void setMaxX(float maxX);

    void setMaxY(float maxY);

    void setMaxZ(float maxZ);

    void setAABB(AABB aabb);
}
