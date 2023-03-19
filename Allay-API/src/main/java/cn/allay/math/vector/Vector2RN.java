package cn.allay.math.vector;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
public interface Vector2RN<T extends Number> {

    static <T extends Number> Vector2RN<T> of(T x, T z) {
        return new ImplVector2RN<>(x, z);
    }

    T getX();

    T getZ();
}
