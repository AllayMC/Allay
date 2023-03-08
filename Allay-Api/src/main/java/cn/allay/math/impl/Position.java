package cn.allay.math.impl;

import cn.allay.level.Level;
import cn.allay.math.interfaces.IPosition;
import lombok.Data;

@Data
public class Position implements IPosition {
    double x;
    double y;
    double z;
    Level level;

    public Position(double x, double y, double z, Level level) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.level = level;
    }

    @Override
    protected IPosition clone() throws CloneNotSupportedException {
        return (IPosition) super.clone();
    }
}
