package cn.allay.worldconverter;

import cn.allay.api.world.DimensionInfo;

public enum Dimension {
    OVERWORLD(DimensionInfo.OVERWORLD),
    NETHER(DimensionInfo.NETHER),
    THE_END(DimensionInfo.THE_END);

    private DimensionInfo info;

    Dimension(DimensionInfo info) {
        this.info = info;
    }

    public DimensionInfo getDimensionInfo() {
        return info;
    }
}
