package cn.allay.math.impl;

import cn.allay.math.interfaces.IVector2;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
@Data
public class Vector2 implements IVector2 {
    double x;
    double z;

    private Vector2(double x, double z) {
        this.x = x;
        this.z = z;
    }

    public static @NotNull IVector2 of(double x, double z) {
        return new Vector2(x, z);
    }

    @Override
    protected IVector2 clone() throws CloneNotSupportedException {
        return (IVector2) super.clone();
    }
}
