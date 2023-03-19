package cn.allay.math.position;

import cn.allay.level.Level;
import cn.allay.math.vector.Vector3RN;

import java.util.Objects;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
public interface PositionRN<T extends Number> extends Vector3RN<T> {

    static <T extends Number> PositionRN<T> of(T x, T y, T z, Level level) {
        Objects.requireNonNull(level);
        return new ImplPositionRN<>(x, y, z, level);
    }

    Level getLevel();
}
