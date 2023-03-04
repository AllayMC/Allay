package cn.allay.math.vector.record;

import cn.allay.math.vector.interfaces.Vector2d;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
public record VectorRecord2d(double x, double z) implements Vector2d {
    @Override
    public Double getX() {
        return x;
    }

    @Override
    public Double getZ() {
        return z;
    }
}
