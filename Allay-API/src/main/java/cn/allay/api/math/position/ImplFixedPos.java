package cn.allay.api.math.position;

import cn.allay.api.world.World;
import lombok.EqualsAndHashCode;

/**
 * @author daoge_cmd <br>
 * @date 2023/3/11 <br>
 * Allay Project <br>
 */
@EqualsAndHashCode
class ImplFixedPos<T extends Number> implements FixedPos<T> {

    protected T x;
    protected T y;
    protected T z;
    protected World world;

    public ImplFixedPos(T x, T y, T z, World world) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.world = world;
    }

    @Override
    public T getX() {
        return x;
    }

    @Override
    public T getY() {
        return y;
    }

    @Override
    public T getZ() {
        return z;
    }

    public World getWorld() {
        return world;
    }
}
