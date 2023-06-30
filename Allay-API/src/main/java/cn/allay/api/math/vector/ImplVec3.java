package cn.allay.api.math.vector;

/**
 * Allay Project 2023/3/11
 *
 * @author daoge_cmd
 */
class ImplVec3<T extends Number> extends ImplFixedVec3<T> implements Vec3<T> {
    public ImplVec3(T x, T y, T z) {
        super(x, y, z);
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
