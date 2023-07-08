package cn.allay.api.math.location;

import cn.allay.api.world.World;
import lombok.EqualsAndHashCode;

/**
 * Allay Project 2023/3/11
 *
 * @author daoge_cmd
 */
@EqualsAndHashCode
class ImplFixedLoc<T extends Number> implements FixedLoc<T> {

    protected T x;
    protected T y;
    protected T z;
    protected double yaw;
    protected double headYaw;
    protected double pitch;
    protected World world;

    public ImplFixedLoc(T x, T y, T z, World world) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.world = world;
    }

    public ImplFixedLoc(T x, T y, T z, double pitch, double yaw, World world) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
        this.world = world;
    }

    public ImplFixedLoc(T x, T y, T z, double pitch, double yaw, double headYaw, World world) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
        this.headYaw = headYaw;
        this.world = world;
    }

    @Override
    public T getX() {
        return x;
    }

    @Override
    public T getY() {
        return y;
    }

    @Override
    public T getZ() {
        return z;
    }

    public World getWorld() {
        return world;
    }

    @Override
    public double getYaw() {
        return yaw;
    }

    @Override
    public double getHeadYaw() {
        return headYaw;
    }

    @Override
    public double getPitch() {
        return pitch;
    }
}
