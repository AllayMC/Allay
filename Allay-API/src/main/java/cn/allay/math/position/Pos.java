package cn.allay.math.position;

import cn.allay.level.Level;
import cn.allay.math.vector.Vec3;

import java.util.Objects;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/11 <br>
 * Allay Project <br>
 */
public interface Pos<T extends Number> extends PosRO<T>, Vec3<T> {

    static <T extends Number> Pos<T> of(T x, T y, T z, Level level) {
        return new ImplPos<>(x, y, z, level);
    }

    void setLevel(Level level);
}
