package cn.allay.api.math.position;

import cn.allay.api.math.vector.Vec3;
import cn.allay.api.world.World;

/**
 * @author daoge_cmd <br>
 * @date 2023/3/11 <br>
 * Allay Project <br>
 */
public interface Pos<T extends Number> extends FixedPos<T>, Vec3<T> {

    static <T extends Number> Pos<T> of(T x, T y, T z, World world) {
        return new ImplPos<>(x, y, z, world);
    }

    void setWorld(World world);
}
