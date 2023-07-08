package cn.allay.api.math.position;

import cn.allay.api.math.vector.Vec3;
import cn.allay.api.world.World;

/**
 * Allay Project 2023/3/11
 *
 * @author daoge_cmd
 */
public interface Pos<T extends Number> extends FixedPos<T>, Vec3<T> {

    static <T extends Number> Pos<T> of(T x, T y, T z, World world) {
        return new ImplPos<>(x, y, z, world);
    }

    void setWorld(World world);

    default void setPos(FixedPos<T> pos) {
        setVec3(pos);
        setWorld(pos.getWorld());
    }
}
