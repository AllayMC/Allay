package cn.allay.math.impl;

import cn.allay.math.interfaces.IVector3;
import lombok.Data;

@Data
public class Vector3 implements IVector3 {
    double x;
    double y;
    double z;

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    protected IVector3 clone() throws CloneNotSupportedException {
        return (IVector3) super.clone();
    }
}
