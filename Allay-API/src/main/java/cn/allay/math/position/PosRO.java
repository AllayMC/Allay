package cn.allay.math.position;

import cn.allay.level.Level;
import cn.allay.math.vector.Vec3RO;

import java.util.Objects;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
public interface PosRO<T extends Number> extends Vec3RO<T> {

    static <T extends Number> PosRO<T> of(T x, T y, T z, Level level) {
        Objects.requireNonNull(level);
        return new ImplPosRO<>(x, y, z, level);
    }

    Level getLevel();
}
