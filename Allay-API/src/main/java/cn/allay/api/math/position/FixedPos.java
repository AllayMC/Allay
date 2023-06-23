package cn.allay.api.math.position;

import cn.allay.api.math.vector.FixedVec3;
import cn.allay.api.world.World;

/**
 * @author daoge_cmd <br>
 * @date 2023/3/4 <br>
 * Allay Project <br>
 */
public interface FixedPos<T extends Number> extends FixedVec3<T> {

    static <T extends Number> FixedPos<T> of(T x, T y, T z, World world) {
        return new ImplFixedPos<>(x, y, z, world);
    }

    World getWorld();
}
