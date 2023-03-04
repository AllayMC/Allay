package cn.allay.math.vector.record;

import cn.allay.math.vector.interfaces.Vector3i;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
public record ImmutableVector3i(int x, int y, int z) implements Vector3i {
    @Override
    public Integer getX() {
        return x;
    }

    @Override
    public Integer getY() {
        return y;
    }

    @Override
    public Integer getZ() {
        return z;
    }
}
