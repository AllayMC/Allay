package cn.allay.api.math.position;

import cn.allay.api.world.World;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/11 <br>
 * Allay Project <br>
 */
class ImplPos<T extends Number> extends ImplFixedPos<T> implements Pos<T> {
    public ImplPos(T x, T y, T z, World world) {
        super(x, y, z, world);
    }

    @Override
    public void setWorld(World world) {
        this.world = world;
    }

    @Override
    public void setX(T value) {
        this.x = value;
    }

    @Override
    public void setY(T value) {
        this.y = value;
    }

    @Override
    public void setZ(T value) {
        this.z = value;
    }
}
