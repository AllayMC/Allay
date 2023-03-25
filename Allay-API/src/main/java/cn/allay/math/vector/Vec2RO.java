package cn.allay.math.vector;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
public interface Vec2RO<T extends Number> {

    static <T extends Number> Vec2RO<T> of(T x, T z) {
        return new ImplVec2RO<>(x, z);
    }

    T getX();

    T getZ();
}
