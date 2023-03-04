package cn.allay.math.vector.record;

import cn.allay.math.vector.interfaces.Vector3d;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
public record ImmutableVector3d(double x, double y, double z) implements Vector3d {
    @Override
    public Double getX() {
        return x;
    }

    @Override
    public Double getY() {
        return y;
    }

    @Override
    public Double getZ() {
        return z;
    }
}
