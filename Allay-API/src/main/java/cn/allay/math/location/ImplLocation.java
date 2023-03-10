package cn.allay.math.location;

import cn.allay.level.Level;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/11 <br>
 * Allay Project <br>
 */
class ImplLocation<T extends Number> extends ImplReadOnlyLocation<T> implements Location<T> {
    public ImplLocation(T x, T y, T z, Level level) {
        super(x, y, z, level);
    }

    public ImplLocation(T x, T y, T z, double yaw, double pitch, Level level) {
        super(x, y, z, yaw, pitch, level);
    }

    public ImplLocation(T x, T y, T z, double yaw, double headYaw, double pitch, Level level) {
        super(x, y, z, yaw, headYaw, pitch, level);
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
    public void setLevel(Level level) {
        this.level = level;
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
