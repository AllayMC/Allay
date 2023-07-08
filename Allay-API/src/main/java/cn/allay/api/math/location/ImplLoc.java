package cn.allay.api.math.location;

import cn.allay.api.world.World;

/**
 * Allay Project 2023/3/11
 *
 * @author daoge_cmd
 */
class ImplLoc<T extends Number> extends ImplFixedLoc<T> implements Loc<T> {
    public ImplLoc(T x, T y, T z, World world) {
        super(x, y, z, world);
    }

    public ImplLoc(T x, T y, T z, double pitch, double yaw, World world) {
        super(x, y, z, pitch, yaw, world);
    }

    public ImplLoc(T x, T y, T z, double pitch, double yaw, double headYaw, World world) {
        super(x, y, z, pitch, yaw, headYaw, world);
    }

    @Override
    public void setPitch(double value) {
        this.pitch = value;
    }

    @Override
    public void setYaw(double value) {
        this.yaw = value;
    }

    @Override
    public void setHeadYaw(double value) {
        this.yaw = value;
    }

    @Override
    public void setWorld(World world) {
        this.world = world;
    }

    @Override
    public void setX(T value) {
        this.x = value;
    }

    @Override
    public void setY(T value) {
        this.y = value;
    }

    @Override
    public void setZ(T value) {
        this.z = value;
    }
}
