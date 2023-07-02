package cn.allay.api.math.position;

import cn.allay.api.math.vector.FixedVec3;
import cn.allay.api.world.World;

/**
 * Allay Project 2023/3/4
 *
 * @author daoge_cmd
 */
public interface FixedPos<T extends Number> extends FixedVec3<T> {

    static <T extends Number> FixedPos<T> of(T x, T y, T z, World world) {
        return new ImplFixedPos<>(x, y, z, world);
    }

    World getWorld();

    default FixedPos<T> cloneFixedPos() {
        return of(getX(), getY(), getZ(), getWorld());
    }

    default Pos<T> clonePos() {
        return Pos.of(getX(), getY(), getZ(), getWorld());
    }
}
