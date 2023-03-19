package cn.allay.math.location;

import cn.allay.level.Level;
import cn.allay.math.position.PositionRN;

import java.util.Objects;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
public interface LocationRN<T extends Number> extends PositionRN<T> {

    static <T extends Number> LocationRN<T> of(T x, T y, T z, Level level) {
        return of(x, y, z, 0, 0, level);
    }

    static <T extends Number> LocationRN<T> of(T x, T y, T z, double yaw, double pitch, Level level) {
        return of(x, y, z, yaw, 0d, pitch, level);
    }

    static <T extends Number> LocationRN<T> of(T x, T y, T z, double yaw, double headYaw, double pitch, Level level) {
        Objects.requireNonNull(level);
        return new ImplLocationRN<>(x, y, z, yaw, headYaw, pitch, level);
    }

    double getPitch();

    double getHeadYaw();

    double getYaw();
}
