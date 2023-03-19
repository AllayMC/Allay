package cn.allay.math.position;

import cn.allay.level.Level;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/11 <br>
 * Allay Project <br>
 */
class ImplPositionRN<T extends Number> implements PositionRN<T> {

    protected T x;
    protected T y;
    protected T z;
    protected Level level;

    public ImplPositionRN(T x, T y, T z, Level level) {
        this.x = x;
        this.y = y;
        this.z = z;
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
}
