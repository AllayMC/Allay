package cn.allay.api.math.vector;

/**
 * Allay Project 2023/3/11
 *
 * @author daoge_cmd
 */
public interface Vec2<T extends Number> extends FixedVec2<T> {

    static <T extends Number> Vec2<T> of(T x, T z) {
        return new ImplVec2<>(x, z);
    }

    void setX(T value);

    void setZ(T value);

    default void setVec2(FixedVec2<T> vec2) {
        setX(vec2.getX());
        setZ(vec2.getZ());
    }
}
