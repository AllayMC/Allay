package cn.allay.math.position;

import cn.allay.level.Level;
import cn.allay.math.vector.Vector3;

import java.util.Objects;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/11 <br>
 * Allay Project <br>
 */
public interface Position<T extends Number> extends PositionRN<T>, Vector3<T> {

    static <T extends Number> Position<T> of(T x, T y, T z, Level level) {
        Objects.requireNonNull(level);
        return new ImplPosition<>(x, y, z, level);
    }

    void setLevel(Level level);
}
