package cn.allay.api.math.location;

import cn.allay.api.math.position.FixedPos;
import cn.allay.api.world.World;

/**
 * @author daoge_cmd <br>
 * @date 2023/3/4 <br>
 * Allay Project <br>
 */
public interface FixedLoc<T extends Number> extends FixedPos<T> {

    static <T extends Number> FixedLoc<T> of(T x, T y, T z, World world) {
        return of(x, y, z, 0, 0, world);
    }

    static <T extends Number> FixedLoc<T> of(T x, T y, T z, double yaw, double pitch, World world) {
        return of(x, y, z, yaw, 0d, pitch, world);
    }

    static <T extends Number> FixedLoc<T> of(T x, T y, T z, double yaw, double headYaw, double pitch, World world) {
        return new ImplFixedLoc<>(x, y, z, yaw, headYaw, pitch, world);
    }

    double getPitch();

    double getHeadYaw();

    double getYaw();
}
