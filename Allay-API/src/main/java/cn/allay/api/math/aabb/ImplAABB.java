package cn.allay.api.math.aabb;

/**
 * Allay Project 2023/3/25
 *
 * @author daoge_cmd
 */
class ImplAABB extends ImplFixedAABB implements AABB {

    public ImplAABB(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        super(minX, minY, minZ, maxX, maxY, maxZ);
    }

    @Override
    public void setMinX(float minX) {
        this.minX = minX;
    }

    @Override
    public void setMinY(float minY) {
        this.minY = minY;
    }

    @Override
    public void setMinZ(float minZ) {
        this.minZ = minZ;
    }

    @Override
    public void setMaxX(float maxX) {
        this.maxX = maxX;
    }

    @Override
    public void setMaxY(float maxY) {
        this.maxY = maxY;
    }

    @Override
    public void setMaxZ(float maxZ) {
        this.maxZ = maxZ;
    }
}
