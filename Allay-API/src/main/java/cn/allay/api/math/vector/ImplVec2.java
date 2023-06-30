package cn.allay.api.math.vector;

/**
 * Allay Project 2023/3/11
 *
 * @author daoge_cmd
 */
class ImplVec2<T extends Number> extends ImplFixedVec2<T> implements Vec2<T> {
    public ImplVec2(T x, T z) {
        super(x, z);
    }

    @Override
    public void setX(T value) {
        this.x = value;
    }

    @Override
    public void setZ(T value) {
        this.z = value;
    }
}
