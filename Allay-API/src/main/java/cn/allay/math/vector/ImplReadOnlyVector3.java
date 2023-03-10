package cn.allay.math.vector;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/11 <br>
 * Allay Project <br>
 */
class ImplReadOnlyVector3<T extends Number> implements ReadOnlyVector3<T> {
    protected T x;
    protected T y;
    protected T z;

    public ImplReadOnlyVector3(T x, T y, T z) {
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
