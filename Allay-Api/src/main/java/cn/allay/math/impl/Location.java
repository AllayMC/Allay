package cn.allay.math.impl;

import cn.allay.level.ILevel;
import cn.allay.math.interfaces.ILocation;
import lombok.Data;

@Data
public class Location implements ILocation {
    double x;
    double y;
    double z;
    double yaw;
    double pitch;
    ILevel level;

    public Location(double x, double y, double z, double yaw, double pitch, ILevel level) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.level = level;
    }

    @Override
    protected Location clone() throws CloneNotSupportedException {
        return (Location) super.clone();
    }
}
