package cn.allay.math.vector.record;

import cn.allay.math.vector.interfaces.Vector3f;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
public record VectorRecord3f(float x, float y, float z) implements Vector3f {
    @Override
    public Float getX() {
        return x;
    }

    @Override
    public Float getY() {
        return y;
    }

    @Override
    public Float getZ() {
        return z;
    }
}
