package cn.allay.math.position.record;

import cn.allay.level.Level;
import cn.allay.math.position.interfaces.Position;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
public record PositionRecord(
        double x,
        double y,
        double z,
        Level level) implements Position {
    @Override
    public Level getLevel() {
        return level;
    }

    @Override
    public Double getX() {
        return x;
    }

    @Override
    public Double getY() {
        return y;
    }

    @Override
    public Double getZ() {
        return z;
    }
}
