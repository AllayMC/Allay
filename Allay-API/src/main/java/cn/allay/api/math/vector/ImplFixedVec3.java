package cn.allay.api.math.vector;

import lombok.EqualsAndHashCode;

/**
 * Allay Project 2023/3/11
 *
 * @author daoge_cmd
 */
@EqualsAndHashCode
class ImplFixedVec3<T extends Number> implements FixedVec3<T> {
    protected T x;
    protected T y;
    protected T z;

    public ImplFixedVec3(T x, T y, T z) {
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
