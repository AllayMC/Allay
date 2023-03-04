package cn.allay.math.location.record;

import cn.allay.level.Level;
import cn.allay.math.location.interfaces.Location;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
public record LocationRecord(
        double x,
        double y,
        double z,
        double yaw,
        double pitch,
        Level level) implements Location {

    @Override
    public Double getYaw() {
        return yaw;
    }

    @Override
    public Double getPitch() {
        return pitch;
    }

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
