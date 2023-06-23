package cn.allay.api.math.vector;

/**
 * @author daoge_cmd <br>
 * @date 2023/3/4 <br>
 * Allay Project <br>
 */
public interface FixedVec2<T extends Number> {

    static <T extends Number> FixedVec2<T> of(T x, T z) {
        return new ImplFixedVec2<>(x, z);
    }

    T getX();

    T getZ();
}
