package cn.allay.math.impl;

import cn.allay.level.ILevel;
import cn.allay.math.interfaces.IPosition;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
@Data
public class Position implements IPosition {
    double x;
    double y;
    double z;
    ILevel level;

    private Position(double x, double y, double z, ILevel level) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.level = level;
    }

    public static @NotNull IPosition of(double x, double y, double z, ILevel level) {
        return new Position(x, y, z, level);
    }
}
