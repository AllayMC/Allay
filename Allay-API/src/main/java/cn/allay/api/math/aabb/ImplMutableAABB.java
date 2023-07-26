package cn.allay.api.math.aabb;

/**
 * Allay Project 2023/3/25
 *
 * @author daoge_cmd
 */
class ImplMutableAABB implements MutableAABB {
    protected double minX;
    protected double minY;
    protected double minZ;
    protected double maxX;
    protected double maxY;
    protected double maxZ;

    public ImplMutableAABB(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        this.minX = minX;
        this.minY = minY;
        this.minZ = minZ;
        this.maxX = maxX;
        this.maxY = maxY;
        this.maxZ = maxZ;
    }

    @Override
    public double minX() {
        return minX;
    }

    @Override
    public double minY() {
        return minY;
    }

    @Override
    public double minZ() {
        return minZ;
    }

    @Override
    public double maxX() {
        return maxX;
    }

    @Override
    public double maxY() {
        return maxY;
    }

    @Override
    public double maxZ() {
        return maxZ;
    }

    @Override
    public void setMinX(double minX) {
        this.minX = minX;
    }

    @Override
    public void setMinY(double minY) {
        this.minY = minY;
    }

    @Override
    public void setMinZ(double minZ) {
        this.minZ = minZ;
    }

    @Override
    public void setMaxX(double maxX) {
        this.maxX = maxX;
    }

    @Override
    public void setMaxY(double maxY) {
        this.maxY = maxY;
    }

    @Override
    public void setMaxZ(double maxZ) {
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
