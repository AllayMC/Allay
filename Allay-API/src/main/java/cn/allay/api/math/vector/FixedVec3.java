package cn.allay.api.math.vector;

/**
 * Allay Project 2023/3/4
 *
 * @author daoge_cmd
 */
public interface FixedVec3<T extends Number> extends FixedVec2<T>{
    static <T extends Number> FixedVec3<T> of(T x, T y, T z) {
        return new ImplFixedVec3<>(x, y, z);
    }

    T getY();

    default FixedVec3<T> cloneFixedVec3() {
        return of(getX(), getY(), getZ());
    }

    default Vec3<T> cloneVec3() {
        return Vec3.of(getX(), getY(), getZ());
    }
}
