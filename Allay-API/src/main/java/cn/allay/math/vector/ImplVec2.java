package cn.allay.math.vector;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/11 <br>
 * Allay Project <br>
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
