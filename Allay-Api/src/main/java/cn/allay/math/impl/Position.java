package cn.allay.math.impl;

import cn.allay.level.Level;
import cn.allay.math.interfaces.IPosition;
import lombok.Data;

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
    Level level;
}
