package cn.allay.math.location;

import cn.allay.level.Level;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/11 <br>
 * Allay Project <br>
 */
class ImplLocRO<T extends Number> implements LocRO<T> {

    protected T x;
    protected T y;
    protected T z;
    protected double yaw;
    protected double headYaw;
    protected double pitch;
    protected Level level;

    public ImplLocRO(T x, T y, T z, Level level) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.level = level;
    }

    public ImplLocRO(T x, T y, T z, double yaw, double pitch, Level level) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.level = level;
    }

    public ImplLocRO(T x, T y, T z, double yaw, double headYaw, double pitch, Level level) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.headYaw = headYaw;
        this.pitch = pitch;
        this.level = level;
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

    @Override
    public Level getLevel() {
        return level;
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
