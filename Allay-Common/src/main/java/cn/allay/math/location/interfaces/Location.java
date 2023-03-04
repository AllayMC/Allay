package cn.allay.math.location.interfaces;

import cn.allay.math.position.interfaces.Position;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
public interface Location extends Position {
    Double getYaw();
    Double getPitch();
}
