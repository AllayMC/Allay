package cn.allay.api.math.aabb;

/**
 * Allay Project 2023/3/25
 *
 * @author daoge_cmd
 */
public interface MutableAABB extends AABB {
    void setMinX(double minX);

    void setMinY(double minY);

    void setMinZ(double minZ);

    void setMaxX(double maxX);

    void setMaxY(double maxY);

    void setMaxZ(double maxZ);

    void setAABB(AABB aabb);
}
