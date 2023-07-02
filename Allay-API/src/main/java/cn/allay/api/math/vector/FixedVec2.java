package cn.allay.api.math.vector;

/**
 * Allay Project 2023/3/4
 *
 * @author daoge_cmd
 */
public interface FixedVec2<T extends Number> {

    static <T extends Number> FixedVec2<T> of(T x, T z) {
        return new ImplFixedVec2<>(x, z);
    }

    T getX();

    T getZ();

    default int getChunkX() {
        return getX().intValue() >> 4;
    }

    default int getChunkZ() {
        return getZ().intValue() >> 4;
    }

    default FixedVec2<T> cloneFixedVec2() {
        return of(getX(), getZ());
    }

    default Vec2<T> cloneVec2() {
        return Vec2.of(getX(), getZ());
    }
}
