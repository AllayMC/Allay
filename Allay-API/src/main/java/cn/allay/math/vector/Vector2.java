package cn.allay.math.vector;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/11 <br>
 * Allay Project <br>
 */
public interface Vector2<T extends Number> extends Vector2RN<T> {

    static <T extends Number> Vector2<T> of(T x, T z) {
        return new ImplVector2<>(x, z);
    }

    void setX(T value);

    void setZ(T value);
}
