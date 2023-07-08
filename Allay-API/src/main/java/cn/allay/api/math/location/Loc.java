package cn.allay.api.math.location;

import cn.allay.api.math.position.Pos;
import cn.allay.api.world.World;

/**
 * Allay Project 2023/3/11
 *
 * @author daoge_cmd
 */
public interface Loc<T extends Number> extends FixedLoc<T>, Pos<T> {

    static <T extends Number> Loc<T> of(T x, T y, T z, World world) {
        return of(x, y, z, 0, 0, world);
    }

    static <T extends Number> Loc<T> of(T x, T y, T z, double yaw, double pitch, World world) {
        return of(x, y, z, yaw, 0d, pitch, world);
    }

    static <T extends Number> Loc<T> of(T x, T y, T z, double yaw, double headYaw, double pitch, World world) {
        return new ImplLoc<>(x, y, z, yaw, headYaw, pitch, world);
    }

    void setPitch(double value);

    void setYaw(double value);

    void setHeadYaw(double value);

    default void setLoc(FixedLoc<T> loc) {
        setPos(loc);
        setYaw(loc.getYaw());
        setHeadYaw(loc.getHeadYaw());
        setPitch(loc.getPitch());
    }
}
