package cn.allay.api.math.vector;

/**
 * @author daoge_cmd <br>
 * @date 2023/3/4 <br>
 * Allay Project <br>
 */
public interface FixedVec3<T extends Number> {
    static <T extends Number> FixedVec3<T> of(T x, T y, T z) {
        return new ImplFixedVec3<>(x, y, z);
    }

    T getX();

    T getY();

    T getZ();
}
