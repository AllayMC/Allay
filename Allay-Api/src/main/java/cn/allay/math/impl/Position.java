package cn.allay.math.impl;

import cn.allay.level.ILevel;
import cn.allay.math.interfaces.IPosition;
import lombok.Data;

@Data
public class Position implements IPosition {
    double x;
    double y;
    double z;
    ILevel level;

    public Position(double x, double y, double z, ILevel level) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.level = level;
    }

    @Override
    protected Position clone() throws CloneNotSupportedException {
        return (Position) super.clone();
    }
}
