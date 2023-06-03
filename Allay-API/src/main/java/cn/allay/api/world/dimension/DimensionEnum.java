package cn.allay.api.world.dimension;

/**
 * Author: Cool_Loong <br>
 * Date: 5/31/2023 <br>
 * Allay Project
 */
public enum DimensionEnum {
    OVERWORLD(new DimensionData(DimensionData.DIMENSION_OVERWORLD, -64, 319, 24)),
    NETHER(new DimensionData(DimensionData.DIMENSION_NETHER, 0, 127, 16)),
    END(new DimensionData(DimensionData.DIMENSION_THE_END, 0, 255, 16));

    private final DimensionData dimensionData;

    DimensionEnum(DimensionData dimensionData) {
        this.dimensionData = dimensionData;
    }

    public static DimensionEnum valueOf(int dimension) {
        for (DimensionEnum value : values()) {
            if (value.getDimensionData().dimensionId() == dimension) {
                return value;
            }
        }
        return OVERWORLD;
    }

    public DimensionData getDimensionData() {
        return this.dimensionData;
    }
}
