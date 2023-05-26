package cn.allay.math.vector;

import lombok.EqualsAndHashCode;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/11 <br>
 * Allay Project <br>
 */
@EqualsAndHashCode
class ImplVec2RO<T extends Number> implements Vec2RO<T> {
    protected T x;
    protected T z;

    public ImplVec2RO(T x, T z) {
        this.x = x;
        this.z = z;
    }

    @Override
    public T getX() {
        return x;
    }

    @Override
    public T getZ() {
        return z;
    }
}
