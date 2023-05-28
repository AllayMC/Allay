package cn.allay.math.vector;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/11 <br>
 * Allay Project <br>
 */
public interface Vec3<T extends Number> extends FixedVec3<T> {

    static <T extends Number> Vec3<T> of(T x, T y, T z) {
        return new ImplVec3<>(x, y, z);
    }

    void setX(T value);

    void setY(T value);

    void setZ(T value);
}
