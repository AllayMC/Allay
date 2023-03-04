package cn.allay.math.vector.record;

import cn.allay.math.vector.interfaces.Vector2i;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
public record VectorRecord2i(int x, int z) implements Vector2i {
    @Override
    public Integer getX() {
        return x;
    }

    @Override
    public Integer getZ() {
        return z;
    }
}
