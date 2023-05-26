package cn.allay.math.vector;

import lombok.EqualsAndHashCode;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/11 <br>
 * Allay Project <br>
 */
@EqualsAndHashCode
class ImplVec3RO<T extends Number> implements Vec3RO<T> {
    protected T x;
    protected T y;
    protected T z;

    public ImplVec3RO(T x, T y, T z) {
        this.x = x;
        this.y = y;
        this.z = z;
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
}
