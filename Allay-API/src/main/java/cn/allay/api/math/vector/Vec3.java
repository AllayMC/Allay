package cn.allay.api.math.vector;

/**
 * Allay Project 2023/3/11
 *
 * @author daoge_cmd
 */
public interface Vec3<T extends Number> extends FixedVec3<T>, Vec2<T>{

    static <T extends Number> Vec3<T> of(T x, T y, T z) {
        return new ImplVec3<>(x, y, z);
    }

    void setY(T value);

    default void setVec3(FixedVec3<T> vec3) {
        setVec2(vec3);
        setY(vec3.getY());
    }
}
