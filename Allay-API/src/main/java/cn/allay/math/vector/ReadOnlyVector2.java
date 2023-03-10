package cn.allay.math.vector;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
public interface ReadOnlyVector2<T extends Number> {

    static <T extends Number> ReadOnlyVector2<T> of(T x, T z) {
        return new ImplReadOnlyVector2<>(x, z);
    }

    T getX();

    T getZ();
}
