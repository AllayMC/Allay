package cn.allay.api.math.location;

import cn.allay.api.level.Level;
import cn.allay.api.math.position.FixedPos;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
public interface FixedLoc<T extends Number> extends FixedPos<T> {

    static <T extends Number> FixedLoc<T> of(T x, T y, T z, Level level) {
        return of(x, y, z, 0, 0, level);
    }

    static <T extends Number> FixedLoc<T> of(T x, T y, T z, double yaw, double pitch, Level level) {
        return of(x, y, z, yaw, 0d, pitch, level);
    }

    static <T extends Number> FixedLoc<T> of(T x, T y, T z, double yaw, double headYaw, double pitch, Level level) {
        return new ImplFixedLoc<>(x, y, z, yaw, headYaw, pitch, level);
    }

    double getPitch();

    double getHeadYaw();

    double getYaw();
}
