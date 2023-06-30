package cn.allay.api.math.vector;

/**
 * Allay Project 2023/3/11
 *
 * @author daoge_cmd
 */
public interface Vec3<T extends Number> extends FixedVec3<T> {

    static <T extends Number> Vec3<T> of(T x, T y, T z) {
        return new ImplVec3<>(x, y, z);
    }

    void setX(T value);

    void setY(T value);

    void setZ(T value);
}
