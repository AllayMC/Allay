package cn.allay.api.math.position;

import cn.allay.api.level.Level;
import cn.allay.api.math.vector.Vec3;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/11 <br>
 * Allay Project <br>
 */
public interface Pos<T extends Number> extends FixedPos<T>, Vec3<T> {

    static <T extends Number> Pos<T> of(T x, T y, T z, Level level) {
        return new ImplPos<>(x, y, z, level);
    }

    void setLevel(Level level);
}
