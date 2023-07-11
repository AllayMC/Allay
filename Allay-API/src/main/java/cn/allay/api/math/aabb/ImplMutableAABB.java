package cn.allay.api.math.aabb;

/**
 * Allay Project 2023/3/25
 *
 * @author daoge_cmd
 */
class ImplMutableAABB implements MutableAABB {
    protected float minX;
    protected float minY;
    protected float minZ;
    protected float maxX;
    protected float maxY;
    protected float maxZ;

    public ImplMutableAABB(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
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


    @Override
    public void setAABB(AABB aabb) {
        this.minX = aabb.minX();
        this.minY = aabb.minY();
        this.minZ = aabb.minZ();
        this.maxX = aabb.minX();
        this.maxY = aabb.minY();
        this.maxZ = aabb.minZ();
    }
}
