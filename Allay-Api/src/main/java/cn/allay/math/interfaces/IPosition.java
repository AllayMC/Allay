package cn.allay.math.interfaces;

import cn.allay.level.Level;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
public interface IPosition extends IVector3 {
    Level getLevel();
}
