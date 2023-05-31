package cn.allay.api.math.vector;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/11 <br>
 * Allay Project <br>
 */
public interface Vec2<T extends Number> extends FixedVec2<T> {

    static <T extends Number> Vec2<T> of(T x, T z) {
        return new ImplVec2<>(x, z);
    }

    void setX(T value);

    void setZ(T value);
}
