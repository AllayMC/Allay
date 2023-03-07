package cn.allay.math.interfaces;

import cn.allay.level.ILevel;

public interface IPosition extends IVector3 {
    ILevel getLevel();
}
