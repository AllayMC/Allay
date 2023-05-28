package cn.allay.math.position;

import cn.allay.level.Level;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/11 <br>
 * Allay Project <br>
 */
class ImplPos<T extends Number> extends ImplFixedPos<T> implements Pos<T> {
    public ImplPos(T x, T y, T z, Level level) {
        super(x, y, z, level);
    }

    @Override
    public void setLevel(Level level) {
        this.level = level;
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
