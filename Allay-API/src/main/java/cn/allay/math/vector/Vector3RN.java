package cn.allay.math.vector;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
public interface Vector3RN<T extends Number> {
    static <T extends Number> Vector3RN<T> of(T x, T y, T z) {
        return new ImplVector3RN<>(x, y, z);
    }

    T getX();

    T getY();

    T getZ();
}
