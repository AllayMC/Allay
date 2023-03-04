package cn.allay.math.vector.record;

import cn.allay.math.vector.interfaces.Vector2f;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
public record ImmutableVector2f(float x, float z) implements Vector2f {
    @Override
    public Float getX() {
        return x;
    }

    @Override
    public Float getZ() {
        return z;
    }
}
