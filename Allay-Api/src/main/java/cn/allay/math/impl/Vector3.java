package cn.allay.math.impl;

import cn.allay.math.interfaces.IVector3;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

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

    private Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static @NotNull IVector3 of(double x, double y, double z) {
        return new Vector3(x, y, z);
    }

    @Override
    protected IVector3 clone() throws CloneNotSupportedException {
        return (IVector3) super.clone();
    }
}
