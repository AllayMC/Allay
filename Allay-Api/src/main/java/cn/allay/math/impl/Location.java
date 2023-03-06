package cn.allay.math.impl;

import cn.allay.level.ILevel;
import cn.allay.math.interfaces.ILocation;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
@Data
public class Location implements ILocation {
    double x;
    double y;
    double z;
    double yaw;
    double pitch;
    ILevel level;

    private Location(double x, double y, double z, double yaw, double pitch, ILevel level) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.level = level;
    }

    public static @NotNull ILocation of(double x, double y, double z, double yaw, double pitch, ILevel level) {
        return new Location(x, y, z, yaw, pitch, level);
    }
}
