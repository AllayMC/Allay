package cn.allay.math.location;

import cn.allay.level.Level;
import cn.allay.math.position.PosRO;

import java.util.Objects;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
public interface LocRO<T extends Number> extends PosRO<T> {

    static <T extends Number> LocRO<T> of(T x, T y, T z, Level level) {
        return of(x, y, z, 0, 0, level);
    }

    static <T extends Number> LocRO<T> of(T x, T y, T z, double yaw, double pitch, Level level) {
        return of(x, y, z, yaw, 0d, pitch, level);
    }

    static <T extends Number> LocRO<T> of(T x, T y, T z, double yaw, double headYaw, double pitch, Level level) {
        Objects.requireNonNull(level);
        return new ImplLocRO<>(x, y, z, yaw, headYaw, pitch, level);
    }

    double getPitch();

    double getHeadYaw();

    double getYaw();
}
