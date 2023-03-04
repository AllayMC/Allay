package cn.allay.math.position.interfaces;

import cn.allay.level.Level;
import cn.allay.math.vector.interfaces.Vector3d;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
public interface Position extends Vector3d {
    Level getLevel();
}
