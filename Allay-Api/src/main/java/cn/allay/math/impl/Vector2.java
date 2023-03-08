package cn.allay.math.impl;

import cn.allay.math.interfaces.IVector2;
import lombok.Data;

@Data
public class Vector2 implements IVector2 {
    double x;
    double z;

    public Vector2(double x, double z) {
        this.x = x;
        this.z = z;
    }

    @Override
    protected Vector2 clone() throws CloneNotSupportedException {
        return (Vector2) super.clone();
    }
}
