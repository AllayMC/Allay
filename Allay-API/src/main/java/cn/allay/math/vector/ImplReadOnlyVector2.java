package cn.allay.math.vector;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/11 <br>
 * Allay Project <br>
 */
class ImplReadOnlyVector2<T extends Number> implements ReadOnlyVector2<T> {
    protected T x;
    protected T z;

    public ImplReadOnlyVector2(T x, T z) {
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
