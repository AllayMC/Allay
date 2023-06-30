package cn.allay.api.math.aabb;

import lombok.EqualsAndHashCode;

/**
 * Allay Project 2023/3/25
 *
 * @author daoge_cmd
 */
@EqualsAndHashCode
class ImplFixedAABB implements FixedAABB {

    protected float minX;
    protected float minY;
    protected float minZ;
    protected float maxX;
    protected float maxY;
    protected float maxZ;

    public ImplFixedAABB(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        this.minX = minX;
        this.minY = minY;
        this.minZ = minZ;
        this.maxX = maxX;
        this.maxY = maxY;
        this.maxZ = maxZ;
    }

    @Override
    public float minX() {
        return minX;
    }

    @Override
    public float minY() {
        return minY;
    }

    @Override
    public float minZ() {
        return minZ;
    }

    @Override
    public float maxX() {
        return maxX;
    }

    @Override
    public float maxY() {
        return maxY;
    }

    @Override
    public float maxZ() {
        return maxZ;
    }
}
