package cn.allay.math.impl;

import cn.allay.math.interfaces.IVector3;
import lombok.Data;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
@Data
public class Vector3 implements IVector3 {
    double x;
    double y;
    double z;
}
