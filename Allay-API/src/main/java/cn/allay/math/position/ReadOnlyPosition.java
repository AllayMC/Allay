package cn.allay.math.position;

import cn.allay.level.Level;
import cn.allay.math.vector.ReadOnlyVector3;

import java.util.Objects;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
public interface ReadOnlyPosition<T extends Number> extends ReadOnlyVector3<T> {

    static <T extends Number> ReadOnlyPosition<T> of(T x, T y, T z, Level level) {
        Objects.requireNonNull(level);
        return new ImplReadOnlyPosition<>(x, y, z, level);
    }

    Level getLevel();
}
