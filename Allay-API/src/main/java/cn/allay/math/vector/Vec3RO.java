package cn.allay.math.vector;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
public interface Vec3RO<T extends Number> {
    static <T extends Number> Vec3RO<T> of(T x, T y, T z) {
        return new ImplVec3RO<>(x, y, z);
    }

    T getX();

    T getY();

    T getZ();
}
